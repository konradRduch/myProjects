; Autor: Konrad Rduch
; Wersja 1.0
;
;
; Parametry wejœciowe:
; RCX - tab[]
; In tab[] array the first element is sumRed, the second element is sumGreen, 
; the third element is sumBlue and the last element is totalWeight
; RDX - weight
; Current filter weight
; R8  - pixelValue
; Current pixelValue
;
;
; Parametry wyjœciowe:
; RCX - tab[]
; Changed values of the array in which the first element is sumRed, 
; the second element is sumGreen, the third element is sumBlue and 
; the last element is totalWeight
;
;

.data
redMask   dd 00FF0000h
greenMask dd 0000FF00h
blueMask  dd 000000FFh
.code 

;opis procecury
;parametry wejsciowe
;
count_asm proc

    ; sumRed = sumRed + (((pixelValue >> 16) & 0xFF) ) * weight;
    mov eax, r8d              ; Transfer pixelValue to eax
    and eax, redMask          ; Mask to get the Red component
    shr eax, 16               ; Shift pixelValue right by 16 bits
    imul eax, edx             ; Multiply Red component by weight
    add [rcx], eax            ; Add to sumRed

    add rcx, 4                ; Increment tab[] index   
    
    ; sumGreen = sumGreen + (((pixelValue >> 8) & 0xFF) ) * weight;
    mov eax, r8d              ; Transfer pixelValue to eax
    and eax, greenMask        ; Mask to get the Green component
    shr eax, 8                ; Shift pixelValue right by 8 bits
    imul eax, edx             ; Multiply Green component by weight
    add [rcx], eax            ; Add to sumGreen

    add rcx, 4                ; Increment tab[] index   
    
    ; sumBlue = sumBlue + ((pixelValue & 0xFF) ) * weight;
    mov eax,r8d               ; Transfer pixelValue to eax
    and eax, blueMask         ; Mask to get the Blue component
    imul eax, edx             ; Multiply Blue component by weight
    add [rcx], eax            ; Add to sumBlue

     add rcx, 4
    ; totalWeight = totalWeight + weight;
    add [rcx], rdx            ; Add weight to totalWeight

   
    ret
count_asm endp
end