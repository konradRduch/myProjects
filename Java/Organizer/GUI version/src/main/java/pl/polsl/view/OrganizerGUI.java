/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.polsl.view;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import pl.polsl.exceptions.IdNotExistsException;
import pl.polsl.exceptions.StartDateIsAfterEndDateException;
import pl.polsl.model.Event;
import pl.polsl.model.EventFileManager;
import pl.polsl.model.EventsModel;
import pl.polsl.model.Task;
import pl.polsl.model.TaskFileManager;
import pl.polsl.model.TasksModel;

@FunctionalInterface
interface Generate {

    /**
     * Abstract method of the interface, which takes two integer values and
     * returns the result of the operation.
     *
     * @param lineInFile Number of line in file.
     * @param offset Number by how much the new value will differ from the old
     * one.
     * @return The result of the operation on the given integers.
     */
    int create(int lineInFile, int offset);
}

/**
 * The OrganizerGUI class represents the main graphical user interface for an
 * organizer application. It extends the javax.swing.JFrame class and provides
 * functionality for managing tasks and events.
 *
 *
 * @author Konrad Rduch
 * @version f3
 * @since p3
 */
public class OrganizerGUI extends javax.swing.JFrame {

    private TasksModel tasksModel;
    private EventsModel eventsModel;
    private String taskFilePath;
    private String eventFilePath;

    /**
     * Creates new form OrganizerGUI
     */
    private void update() {
        TaskFileManager readData = new TaskFileManager(taskFilePath);
        this.tasksModel = new TasksModel(readData.readFromFile());

        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);
        DefaultTableModel modelCompleted = (DefaultTableModel) jTable3.getModel();
        modelCompleted.setRowCount(0);
        DefaultTableModel modelUncompleted = (DefaultTableModel) jTable5.getModel();
        modelUncompleted.setRowCount(0);
        Vector<Task> tasks = new Vector<>(tasksModel.getTasks());
        for (Task task : tasks) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            model.addRow(rowData);
        }

        Vector<Task> tasksCompleted = new Vector<>(this.tasksModel.filterTasksByStatus(true));
        for (Task task : tasksCompleted) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            modelCompleted.addRow(rowData);
        }

        Vector<Task> tasksUncompleted = new Vector<>(this.tasksModel.filterTasksByStatus(false));
        for (Task task : tasksUncompleted) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            modelUncompleted.addRow(rowData);
        }
    }

    private void updateEvent() {
        EventFileManager readDataEvent = new EventFileManager(eventFilePath);
        this.eventsModel = new EventsModel(readDataEvent.readFromFile());

        DefaultTableModel modelTask = (DefaultTableModel) jTable7.getModel();
        modelTask.setRowCount(0);
        DefaultTableModel modelEnded = (DefaultTableModel) jTable11.getModel();
        modelEnded.setRowCount(0);
        DefaultTableModel modelIncoming = (DefaultTableModel) jTable8.getModel();
        modelIncoming.setRowCount(0);
        DefaultTableModel modelOngoing = (DefaultTableModel) jTable9.getModel();
        modelOngoing.setRowCount(0);

        Vector<Event> events = new Vector<>(eventsModel.getEvents());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for (Event event : events) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelTask.addRow(rowData);
        }
        Vector<Event> eventsEnded = new Vector<>(this.eventsModel.getEndedEvents());
        for (Event event : eventsEnded) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelEnded.addRow(rowData);
        }
        Vector<Event> eventsIncoming = new Vector<>(this.eventsModel.getIncomingEvents());
        for (Event event : eventsIncoming) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelIncoming.addRow(rowData);
        }
        Vector<Event> eventsOngoing = new Vector<>(this.eventsModel.getOngoingEvents());
        for (Event event : eventsOngoing) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelOngoing.addRow(rowData);
        }
    }

    public OrganizerGUI(String taskFilePath, String eventFilePath) {
        this.taskFilePath = taskFilePath;
        this.eventFilePath = eventFilePath;

        initComponents();

        EventFileManager readDataEvent = new EventFileManager(eventFilePath);
        this.eventsModel = new EventsModel(readDataEvent.readFromFile());

        DefaultTableModel modelTask = (DefaultTableModel) jTable7.getModel();
        DefaultTableModel modelEnded = (DefaultTableModel) jTable11.getModel();
        DefaultTableModel modelIncoming = (DefaultTableModel) jTable8.getModel();
        DefaultTableModel modelOngoing = (DefaultTableModel) jTable9.getModel();

        Vector<Event> events = new Vector<>(eventsModel.getEvents());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for (Event event : events) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelTask.addRow(rowData);
        }
        Vector<Event> eventsEnded = new Vector<>(this.eventsModel.getEndedEvents());
        for (Event event : eventsEnded) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelEnded.addRow(rowData);
        }
        Vector<Event> eventsIncoming = new Vector<>(this.eventsModel.getIncomingEvents());
        for (Event event : eventsIncoming) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelIncoming.addRow(rowData);
        }
        Vector<Event> eventsOngoing = new Vector<>(this.eventsModel.getOngoingEvents());
        for (Event event : eventsOngoing) {
            // Format the start and end dates using the SimpleDateFormat
            String formattedStartDate = dateFormat.format(event.getStartDate());
            String formattedEndDate = dateFormat.format(event.getEndDate());

            Object[] rowData = {event.getId(), event.getTitle(), formattedStartDate, formattedEndDate, event.getDescription()};
            modelOngoing.addRow(rowData);
        }

        TaskFileManager readData = new TaskFileManager(taskFilePath);
        this.tasksModel = new TasksModel(readData.readFromFile());

        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        DefaultTableModel modelCompleted = (DefaultTableModel) jTable3.getModel();
        DefaultTableModel modelUncompleted = (DefaultTableModel) jTable5.getModel();

        Vector<Task> tasks = new Vector<>(tasksModel.getTasks());
        for (Task task : tasks) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            model.addRow(rowData);
        }

        Vector<Task> tasksCompleted = new Vector<>(this.tasksModel.filterTasksByStatus(true));
        for (Task task : tasksCompleted) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            modelCompleted.addRow(rowData);
        }

        Vector<Task> tasksUncompleted = new Vector<>(this.tasksModel.filterTasksByStatus(false));
        for (Task task : tasksUncompleted) {
            Object[] rowData = {task.getId(), task.getDescription(), task.isDone()};
            modelUncompleted.addRow(rowData);
        }

        KeyStroke editEventShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK);
        jButton13.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(editEventShortcut, "altE");
        jButton13.getActionMap().put("altE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton13ActionPerformed(e);
            }
        });

        KeyStroke addEventShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK);
        jButton11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(addEventShortcut, "altA");
        jButton11.getActionMap().put("altA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton11ActionPerformed(e);
            }
        });

        KeyStroke deleteEventShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        jButton6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(deleteEventShortcut, "altD");
        jButton6.getActionMap().put("altD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton6ActionPerformed(e);
            }
        });

        KeyStroke editTaskShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK);
        jButton7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(editTaskShortcut, "altE");
        jButton7.getActionMap().put("altE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton7ActionPerformed(e);
            }
        });

        KeyStroke addTaskShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK);
        jButton5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(addTaskShortcut, "altA");
        jButton5.getActionMap().put("altA", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton5ActionPerformed(e);
            }
        });

        KeyStroke deleteTaskShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK);
        jButton8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(deleteTaskShortcut, "altD");
        jButton8.getActionMap().put("altD", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton8ActionPerformed(e);
            }
        });

        KeyStroke toggleTaskShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK);
        jButton9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(toggleTaskShortcut, "altT");
        jButton9.getActionMap().put("altT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton9ActionPerformed(e);
            }
        });

        KeyStroke escapeEventShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        jButton16.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeEventShortcut, "escape");
        jButton16.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton16ActionPerformed(e);
            }
        });

        KeyStroke escapeTaskShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        jButton2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeTaskShortcut, "escape");
        jButton2.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2ActionPerformed(e);
            }
        });
        KeyStroke escapeMainMenuShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        jButton4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeMainMenuShortcut, "escape");
        jButton4.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4ActionPerformed(e);
            }
        });
        KeyStroke taskMainMenuShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK);
        jButton3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(taskMainMenuShortcut, "task");
        jButton3.getActionMap().put("task", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton3ActionPerformed(e);
            }
        });
        KeyStroke eventMainMenuShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK);
        jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(eventMainMenuShortcut, "event");
        jButton1.getActionMap().put("event", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Background = new javax.swing.JPanel();
        MainMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Task = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Event = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setLayout(new java.awt.CardLayout());

        jButton1.setText("Event");
        jButton1.setToolTipText("Event ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Main Menu");

        jButton3.setText("Task");
        jButton3.setToolTipText("Task");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Exit");
        jButton4.setToolTipText("Exit application");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainMenuLayout = new javax.swing.GroupLayout(MainMenu);
        MainMenu.setLayout(MainMenuLayout);
        MainMenuLayout.setHorizontalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addGroup(MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jButton1)))
                    .addGroup(MainMenuLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel1)))
                .addContainerGap(386, Short.MAX_VALUE))
        );
        MainMenuLayout.setVerticalGroup(
            MainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addGap(33, 33, 33)
                .addComponent(jButton4)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        Background.add(MainMenu, "card2");

        jButton2.setText("Back to menu");
        jButton2.setToolTipText("Back to menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Task");

        jButton5.setText("Add");
        jButton5.setToolTipText("Add task");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setText("Edit");
        jButton7.setToolTipText("Edit task");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.setToolTipText("Delete task");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Toggle status");
        jButton9.setToolTipText("Toggle task");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel4.setText("Enter task description");

        jTextField2.setToolTipText("");
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel5.setText("Enter task id you want to edit");

        jLabel6.setText("Enter new task description");
        jLabel6.setToolTipText("");

        jLabel7.setText("Enter task id you want to toggle/delete");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setText("ALL TASK");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
        }

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getColumnModel().getColumn(1).setResizable(false);
            jTable4.getColumnModel().getColumn(2).setResizable(false);
        }

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Description", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
            jTable5.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel9.setText("COMPLETED TASKS");

        jLabel10.setText("UNCOMPLETED TASKS");

        javax.swing.GroupLayout TaskLayout = new javax.swing.GroupLayout(Task);
        Task.setLayout(TaskLayout);
        TaskLayout.setHorizontalGroup(
            TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskLayout.createSequentialGroup()
                                .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(TaskLayout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(170, 170, 170))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(202, 202, 202))))
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(210, 210, 210))))
            .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskLayout.createSequentialGroup()
                    .addGap(382, 382, 382)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addGap(39, 39, 39)))
        );
        TaskLayout.setVerticalGroup(
            TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaskLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(127, 127, 127)))
                .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaskLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaskLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton8))
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)))
                .addGap(14, 14, 14))
            .addGroup(TaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(TaskLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addGap(269, 269, 269)))
        );

        Background.add(Task, "card3");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Event");

        jButton11.setText("Add");
        jButton11.setToolTipText("Adding new event");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton13.setText("Edit");
        jButton13.setToolTipText("Edit event");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton16.setText("Back to menu");
        jButton16.setToolTipText("Back to menu");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Start date", "End date", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable7.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(0).setResizable(false);
            jTable7.getColumnModel().getColumn(1).setResizable(false);
            jTable7.getColumnModel().getColumn(2).setResizable(false);
            jTable7.getColumnModel().getColumn(3).setResizable(false);
            jTable7.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton6.setText("Delete");
        jButton6.setToolTipText("Delete event");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Start date", "End date", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable8.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable8.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(0).setResizable(false);
            jTable8.getColumnModel().getColumn(1).setResizable(false);
            jTable8.getColumnModel().getColumn(2).setResizable(false);
            jTable8.getColumnModel().getColumn(3).setResizable(false);
            jTable8.getColumnModel().getColumn(4).setResizable(false);
        }

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Start date", "End date", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable9.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable9.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(0).setResizable(false);
            jTable9.getColumnModel().getColumn(1).setResizable(false);
            jTable9.getColumnModel().getColumn(2).setResizable(false);
            jTable9.getColumnModel().getColumn(3).setResizable(false);
            jTable9.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel11.setText("ALL EVENTS");

        jLabel12.setText("ENDED EVENTS");

        jLabel13.setText("INCOMING EVENTS");

        jLabel14.setText("ONGOING EVENTS");

        jLabel15.setText("Enter event id to delete");

        jLabel16.setText("New start date");

        jLabel17.setText("New end date");

        jLabel18.setText("New tittle");

        jLabel19.setText("New description");
        jLabel19.setToolTipText("");

        jLabel20.setText("Start date");

        jLabel21.setText("End date");

        jLabel22.setText("Tittle");

        jLabel23.setText("Description");

        jLabel24.setText("Event id to edit");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Start date", "End date", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable11.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable11.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(jTable11);
        if (jTable11.getColumnModel().getColumnCount() > 0) {
            jTable11.getColumnModel().getColumn(0).setResizable(false);
            jTable11.getColumnModel().getColumn(1).setResizable(false);
            jTable11.getColumnModel().getColumn(2).setResizable(false);
            jTable11.getColumnModel().getColumn(3).setResizable(false);
            jTable11.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout EventLayout = new javax.swing.GroupLayout(Event);
        Event.setLayout(EventLayout);
        EventLayout.setHorizontalGroup(
            EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(jButton16)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(290, 290, 290)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(182, 182, 182))))
                    .addGroup(EventLayout.createSequentialGroup()
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(EventLayout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel22)))
                                    .addGroup(EventLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton11)
                                            .addComponent(jButton13)
                                            .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGroup(EventLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel18))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel23)
                                    .addGroup(EventLayout.createSequentialGroup()
                                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EventLayout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(jLabel19))
                                    .addGroup(EventLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))))
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(207, 207, 207))
                                    .addGroup(EventLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(186, 186, 186))
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))))
                .addGap(22, 22, 22))
        );
        EventLayout.setVerticalGroup(
            EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EventLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(EventLayout.createSequentialGroup()
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EventLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(8, 8, 8)))))
                .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EventLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                    .addGroup(EventLayout.createSequentialGroup()
                        .addGroup(EventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EventLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EventLayout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton16)))
                .addContainerGap())
        );

        Background.add(Event, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Invoked when the button with the identifier jButton1 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Hides the MainMenu
     * component. 2. Makes the Event component visible.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MainMenu.setVisible(false);
        Event.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Invoked when the button with the identifier jButton3 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Hides the MainMenu
     * component. 2. Makes the Task component visible.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MainMenu.setVisible(false);
        Task.setVisible(true);


    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Invoked when the button with the identifier jButton2 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Hides the Task component.
     * 2. Makes the MainMenu component visible.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Task.setVisible(false);
        MainMenu.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Invoked when the button with the identifier jButton4 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Disposes of the current
     * window, closing it.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed

    }//GEN-LAST:event_jTextField4ActionPerformed

    /**
     * Invoked when the button with the identifier jButton5 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Retrieves the maximum index
     * for tasks using the tasksModel. 2. Retrieves the task description from
     * the jTextField1 component. 3. Creates a new Task object with the acquired
     * ID, description, and a default completion status. 4. Adds the newly
     * created task to the tasksModel. 5. Writes the updated tasks list to a
     * file using a TaskFileManager. 6. Invokes the update() method to refresh
     * the UI or perform additional updates. 7. Clears the text in the
     * jTextField1 component.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //add
        int id = tasksModel.getMaxIndex();
        String description = jTextField1.getText();

        Task task = new Task(id, description, false);
        tasksModel.addTask(task);

        TaskFileManager saveData = new TaskFileManager(taskFilePath);
        saveData.writeToFile(tasksModel.getTasks());

        update();
        jTextField1.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * Invoked when the button with the identifier jButton8 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the
     * integer value entered in the jTextField4 component. a. If successful,
     * proceeds to the next steps. b. If parsing fails, displays an error
     * message and terminates the method. 2. Deletes the task with the specified
     * ID from the tasksModel. 3. Writes the updated tasks list to a file using
     * a TaskFileManager. 4. Invokes the update() method to refresh the UI or
     * perform additional updates. 5. Clears the text in the jTextField4
     * component.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //delete
        try {
            int idInput = Integer.parseInt(jTextField4.getText());

            tasksModel.deleteTask(idInput);

            TaskFileManager saveData = new TaskFileManager(taskFilePath);
            saveData.writeToFile(tasksModel.getTasks());
            update();
            jTextField4.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entered value is not an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IdNotExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * Invoked when the button with the identifier jButton9 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the
     * integer value entered in the jTextField4 component. a. If successful,
     * proceeds to the next steps. b. If parsing fails, displays an error
     * message and terminates the method. 2. Toggles the completion status of
     * the task with the specified ID in the tasksModel. 3. Writes the updated
     * tasks list to a file using a TaskFileManager. 4. Invokes the update()
     * method to refresh the UI or perform additional updates. 5. Clears the
     * text in the jTextField4 component.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //toggle
        try {
            int idInput = Integer.parseInt(jTextField4.getText()); // Consume the newline character
            tasksModel.toggleTaskStatus(idInput);

            TaskFileManager saveData = new TaskFileManager(taskFilePath);
            saveData.writeToFile(tasksModel.getTasks());
            update();
            jTextField4.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entered value is not an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IdNotExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton9ActionPerformed
    /**
     * Invoked when the button with the identifier jButton7 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the
     * integer value entered in the jTextField2 component. a. If successful,
     * proceeds to the next steps. b. If parsing fails, displays an error
     * message and terminates the method. 2. Retrieves the new description
     * entered in the jTextField3 component. 3. Creates a new Task object with
     * the acquired ID, new description, and a default completion status. 4.
     * Edits the task in the tasksModel with the new information. 5. Writes the
     * updated tasks list to a file using a TaskFileManager. 6. Invokes the
     * update() method to refresh the UI or perform additional updates. 7.
     * Clears the text in the jTextField2 and jTextField3 components.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //edit
        try {
            int idInput = Integer.parseInt(jTextField2.getText()); // Consume the newline character
            String newDescription = jTextField3.getText();

            Task editedTask = new Task(idInput, newDescription, false);
            tasksModel.editTask(editedTask);

            TaskFileManager saveData = new TaskFileManager(taskFilePath);
            saveData.writeToFile(tasksModel.getTasks());
            update();
            jTextField2.setText("");
            jTextField3.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entered value is not an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IdNotExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Invoked when the button with the identifier jButton16 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Hides the Event component.
     * 2. Makes the MainMenu component visible.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        Event.setVisible(false);
        MainMenu.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    /**
     * Invoked when the button with the identifier jButton6 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the
     * integer value entered in the jTextField5 component. a. If successful,
     * proceeds to the next steps. b. If parsing fails, displays an error
     * message and terminates the method. 2. Deletes the event with the
     * specified ID from the eventsModel. 3. Writes the updated events list to a
     * file using an EventFileManager. 4. Invokes the updateEvent() method to
     * refresh the UI or perform additional updates related to events.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            int idInput = Integer.parseInt(jTextField5.getText());
            this.eventsModel.deleteEvent(idInput);
            EventFileManager saveData = new EventFileManager(eventFilePath);
            saveData.writeToFile(eventsModel.getEvents());
            updateEvent();

        } catch (IdNotExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entered value is not an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * Invoked when the button with the identifier jButton11 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the start
     * and end date values entered in the jTextField10 and jTextField11
     * components. a. If successful, proceeds to the next steps. b. If parsing
     * fails, displays an error message and terminates the method. 2. Retrieves
     * the event name, event description, and creates a new Event object with
     * the acquired information. 3. Generates a new ID for the event using a
     * lambda expression with a defined interface. 4. Adds the newly created
     * event to the eventsModel. 5. Writes the updated events list to a file
     * using an EventFileManager. 6. Invokes the updateEvent() method to refresh
     * the UI or perform additional updates related to events.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {
            String startDateInput = jTextField10.getText();
            String endDateInput = jTextField11.getText();

            Date startDate = null;
            Date endDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            try {
                startDate = dateFormat.parse(startDateInput);
                endDate = dateFormat.parse(endDateInput);
            } catch (ParseException e) {

                JOptionPane.showMessageDialog(null, "Invalid date format. Failed to add the event. Correct format is 'dd-MM-yyyy HH:mm'", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String eventName = jTextField12.getText();
            String eventDescription = jTextField13.getText();

            EventFileManager saveData = new EventFileManager(eventFilePath);

            //lambda with defined interface 
            Generate newId = (lineInFile, offset) -> (lineInFile / 5) + offset;
            //Generating id for a new event
            int id = eventsModel.getMaxIndex();

            Event event = new Event(id, eventName, startDate, endDate, eventDescription);
            eventsModel.addEvent(event);

            saveData.writeToFile(eventsModel.getEvents());
            updateEvent();

        } catch (StartDateIsAfterEndDateException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Invoked when the button with the identifier jButton13 is clicked. This
     * method handles the action event triggered by the button click.
     *
     * The method performs the following actions: 1. Attempts to parse the
     * integer value entered in the jTextField14 component. a. If successful,
     * proceeds to the next steps. b. If parsing fails, displays an error
     * message and terminates the method. 2. Attempts to parse the start and end
     * date values entered in the jTextField6 and jTextField7 components. a. If
     * successful, proceeds to the next steps. b. If parsing fails, displays an
     * error message and terminates the method. 3. Retrieves the event name,
     * event description, and creates a new Event object with the acquired
     * information. 4. Edits the event in the eventsModel with the new
     * information. 5. Writes the updated events list to a file using an
     * EventFileManager. 6. Invokes the updateEvent() method to refresh the UI
     * or perform additional updates related to events.
     *
     * @param evt The ActionEvent representing the button click event.
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:

        try {

            int idInput = Integer.parseInt(jTextField14.getText());

            String startDateInput = jTextField6.getText();

            String endDateInput = jTextField7.getText();

            Date startDate = null;
            Date endDate = null;

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            try {
                startDate = dateFormat.parse(startDateInput);
                endDate = dateFormat.parse(endDateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use 'dd-MM-yyyy HH:mm'.");
                return;
            }

            String eventName = jTextField8.getText();

            String eventDescription = jTextField9.getText();

            Event event = new Event(idInput, eventName, startDate, endDate, eventDescription);

            this.eventsModel.editEvent(event);

            EventFileManager saveData = new EventFileManager(eventFilePath);
            saveData.writeToFile(eventsModel.getEvents());
            updateEvent();

        } catch (IdNotExistsException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (StartDateIsAfterEndDateException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entered value is not an integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Event;
    private javax.swing.JPanel MainMenu;
    private javax.swing.JPanel Task;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
