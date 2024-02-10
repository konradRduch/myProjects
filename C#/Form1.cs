using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JAProjekt
{
    public partial class Form1 : Form
    {
        private Bitmap pictureBefore;
        private Bitmap pictureAfter;
        private bool state;
        private bool isPhotoChosen;
        private int chosenFilter;

        private  int[] HighPassFilter1 = { -1, -1, -1, -1, 9, -1, -1, -1, -1 };
        private  int[] HighPassFilter2 = { 0, -1, 0, -1, 5, -1, 0, -1, 0 };
        private  int[] HighPassFilter3 = { 1, -2, 1, -2, 5, -2, 1, -2, 1 };
        private  int[] HighPassFilter4 = { 0, -1, 0, -1, 20, -1, 0, -1, 0 };

       [DllImport(@"D:\c#\projekt-assembler\JaProj\x64\Debug\JAAsm.dll", CallingConvention = CallingConvention.Cdecl)]
        public static extern unsafe void count_asm(int[]tab, int weight, int pixelValue);
        
        public Form1()
        {
            state = true;
            isPhotoChosen = false; 
            InitializeComponent();
        }

        private Bitmap filtruj(Bitmap inputImage, int[] HighPassFilter,int mode)
        {

           int width = inputImage.Width;
           int height = inputImage.Height;

            int[] inputImageArray = ConvertBitmapToArray(inputImage);
            int[] outputImageArray = ApplyHighPassFilter(inputImageArray, width, height,HighPassFilter, mode);
       
          
            Bitmap outputImage = ConvertArrayToBitmap(outputImageArray, width, height);

            outputImage.Save("D:\\c#\\projekt-assembler\\JaProj\\output.jpg");
            return outputImage;
        }

        public int[] ConvertBitmapToArray(Bitmap bitmap)
        {
            int width = bitmap.Width;
            int height = bitmap.Height;

            int[] imageArray = new int[width * height];

            BitmapData bitmapData = bitmap.LockBits(new Rectangle(0, 0, width, height), ImageLockMode.ReadOnly, PixelFormat.Format24bppRgb);

            unsafe
            {
                byte* ptr = (byte*)bitmapData.Scan0;

                for (int y = 0; y < height; y++)
                {
                    for (int x = 0; x < width; x++)
                    {
                        int index = y * width + x;
                        imageArray[index] = ptr[2] << 16 | ptr[1] << 8 | ptr[0]; // Format RGB, wartości pikseli jako 24-bitowe
                        ptr += 3; // Przesunięcie o 3 bajty na piksel (RGB)
                    }
                }
            }

            bitmap.UnlockBits(bitmapData);

            return imageArray;
        }

        public void count(ref int sumRed,ref int sumGreen, ref int sumBlue,int weight,ref int totalWeight, int pixelValue)
        {
            sumRed = sumRed +( ((pixelValue >> 16) & 0xFF) )* weight;
            sumGreen = sumGreen + (((pixelValue >> 8) & 0xFF) )* weight;
            sumBlue = sumBlue + ((pixelValue & 0xFF) )* weight;
            totalWeight = totalWeight + weight;
        }

        public int[] ApplyHighPassFilter(int[] inputImage, int width, int height, int[] HighPassFilter,int mode)
        {
            int[] outputImage = new int[width * height];

            for (int i = 1; i < width - 1; i++)
            {
                for (int j = 1; j < height - 1; j++)
                {
                    int sumRed = 0;
                    int sumGreen = 0;
                    int sumBlue = 0;
                    int totalWeight = 0;

                    for (int x = -1; x <= 1; x++)
                    {
                        for (int y = -1; y <= 1; y++)
                        {
                            int pixelIndex = (i + x) + (j + y) * width;
                            int weight = HighPassFilter[(x + 1) + (y + 1) * 3];
                            int pixelValue = inputImage[pixelIndex];

                            int []tab = new int[4];
                            tab[0] = sumRed;
                            tab[1] = sumGreen;
                            tab[2] = sumBlue;
                            tab[3] = totalWeight;

                            // Check if the pixel is within the image boundaries
                            if (pixelIndex >= 0 && pixelIndex < inputImage.Length)
                            {
                                if(mode == 1)
                                {//c#
                                    count(ref sumRed, ref sumGreen, ref sumBlue, weight, ref totalWeight, pixelValue);
                                }
                                else if(mode == 2)
                                {//asm
                                    count_asm(tab, weight, pixelValue);
                                    sumRed = tab[0];
                                    sumGreen = tab[1];
                                    sumBlue = tab[2];
                                    totalWeight = tab[3];
                                }
                               
                            }
                        }
                    }

                    if (totalWeight != 0)
                    {
                        int newRed = Math.Min(Math.Max(sumRed / totalWeight, 0), 255);
                        int newGreen = Math.Min(Math.Max(sumGreen / totalWeight, 0), 255);
                        int newBlue = Math.Min(Math.Max(sumBlue / totalWeight, 0), 255);

                        outputImage[i + j * width] = (newRed << 16) | (newGreen << 8) | newBlue;
                    }
                    else
                    {
                        // If totalWeight is 0, assign the original pixel value
                        outputImage[i + j * width] = inputImage[i + j * width];
                    }
                }
            }

            return outputImage;
        }


        public Bitmap ConvertArrayToBitmap(int[] imageArray, int width, int height)
        {
            Bitmap outputImage = new Bitmap(width, height);

            for (int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    int index = j * width + i;
                    int pixelValue = imageArray[index];

                    int red = (pixelValue >> 16) & 0xFF;
                    int green = (pixelValue >> 8) & 0xFF;
                    int blue = pixelValue & 0xFF;

                    Color color = Color.FromArgb(red, green, blue);
                    outputImage.SetPixel(i, j, color);
                }
            }

            return outputImage;
        }


        private void button1_Click_1(object sender, EventArgs e)
        {

            if (isPhotoChosen)
            {


                if (state)
                {
                    //c#
                    Stopwatch stopwatch = Stopwatch.StartNew();
                    if (chosenFilter == 0)
                    {

                        pictureAfter = filtruj(pictureBefore, HighPassFilter1, 1);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 1)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter2, 1);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 2)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter3, 1);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 3)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter4, 1);
                        pictureBox2.Image = pictureAfter;
                    }
                    this.Controls.Add(pictureBox2);
                    stopwatch.Stop();
                    long elapsedTime = stopwatch.ElapsedMilliseconds;
                    listBox2.Items.Add($"{elapsedTime} ms");

                }
                else
                {
                    //asm
                    Stopwatch stopwatch = Stopwatch.StartNew();
                    if (chosenFilter == 0)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter1, 2);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 1)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter2, 2);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 2)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter3, 2);
                        pictureBox2.Image = pictureAfter;
                    }
                    else if (chosenFilter == 3)
                    {
                        pictureAfter = filtruj(pictureBefore, HighPassFilter4, 2);
                        pictureBox2.Image = pictureAfter;
                    }
                    this.Controls.Add(pictureBox2);
                    stopwatch.Stop();
                    long elapsedTime = stopwatch.ElapsedMilliseconds;
                    listBox1.Items.Add($"{elapsedTime} ms");
                }

            }
            else
            {
                MessageBox.Show("You haven't selected a photo", "Error",MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            
        }

        private void listBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label5_Click(object sender, EventArgs e)
        {

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
  
        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.Text == "Usuń średnią")
            {
                chosenFilter = 0;
            }else if (comboBox1.Text == "HP1")
            {
                chosenFilter=1;
            }
            else if (comboBox1.Text == "HP2")
            {
                chosenFilter = 2;
            }
            else if (comboBox1.Text == "HP3")
            {
                chosenFilter = 3;
            }

        }

        private void button2_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog dlg = new OpenFileDialog())
            {
                dlg.Title = "Open Image";
                dlg.Filter = "bmp files (*.bmp)|*.bmp";

                if (dlg.ShowDialog() == DialogResult.OK)
                {
                    pictureBefore = new Bitmap(dlg.FileName);
                    pictureBox1.Image = pictureBefore;
                    this.Controls.Add(pictureBox1);
                    isPhotoChosen = true;
                }
            }

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            state = true;
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {

        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            state = false;
        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {
            chosenFilter = 0;
            comboBox1.Text = "Usuń średnią";
        }
    }
}
