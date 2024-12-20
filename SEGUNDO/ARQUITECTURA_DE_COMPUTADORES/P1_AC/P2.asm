.model small    

.code   

org 100h
;este programa lo que hace es activar una interrupcion que  
;genera  el temporizador del sistema con una frecuencia de 18,2 veces por segundo

Programa_Int:  
                            
    jmp Reside ;salto a reside
                                     
    contador db 0   ;define una variable y la inicialliza a 0 

Rutina_Servicio proc  
    
    cli   ;deshabilitamos las interrupciones

    inc contador  ;incrementamos contador a 1 
    cmp contador, 1 ;comparamos contador con 1
    jne fin   ;si contador no es 1 salta a fin
    
    mov ah, 0;interrupcion. lee un caracter desde teclado
    int 16h  ;interrupcion que controla la entrada y salida desde teclado
fin:       

    sti ;activa las interrupcciones
    iret;indica que ha terminado la interrupcion  
endp     

Reside:
    mov dx, offset Rutina_Servicio ;movemos al registro segmento de datos la direccion donde se encuentra la rutina de servicio
    mov ax, 0
    mov es, ax
    mov si, 1Ch*4 ;Interrupcion que llama al reloj cada cierto tiempo*4 que es el tama�o del vector(1s la int se ejecuta 18.2 vces)
	             ; Lo que vamos a hacer es poner la direccion de la Rutina de Servicio en la direccion 1Ch, asi, cada vez que el contador llame a la
                 ; interrupcion 1Ch el ordenador se encontrara la direccion de memoria de la Rutina de servicio y empezara a ejecutarla
    cli  ;desaciva las interrupciones
    mov es:[si], dx ;mueve al segmento extra la direccion de la rutina de servicio
    mov es:[si+2], cs
    sti
    mov dx, offset Reside
    int 27h
    
end Programa_Int
