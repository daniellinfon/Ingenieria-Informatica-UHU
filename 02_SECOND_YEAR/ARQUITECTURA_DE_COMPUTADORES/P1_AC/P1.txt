.model small

.data  
;lo primero que hacemos es definir la cadena que queremos mostrar por pantalla
texto db " Practica1*"
acumulador db 30h   ;definimos una variable que nos servira para imprimir por pantalla un caracter del codigo ASCII que nos ayuda a identificar 
                  ;cuantas veces se ha imprimido por pantalla la cadena  
color db 02h   
      
.code      
                  
	mov ax, seg texto; movemos el segmento texto al registro acumlador
	mov ds, ax  ; movemos el segmento de texto guardado en el registro acumulador al registro que guarda donde comienza el segmento de datos
	
	;hacemos la interrupcion para establecer el modo texto  
	mov al, 03h ;establecemos el modo texto
	mov ah, 00h
	int 10h
	
	mov ax, 0B800h ;guardamos en el registro acumulador la direccion de memoria modo texto
	mov es, ax ;movemos la direccion de memoria del modo texto al segmento extra
	;el segmento extra nos permite acceder a un segmento distinto sin necesidad de modificar
	;los registros                        
	         
	   
	;inicializamos a 0 los registros indice si y di. los utilizaremos para hacer las "cuentas"
	;de cual es el caracter que toca imprimir por pantalla y a que posicion debe ir el siguiente
	
	mov si, 0 ;controlar array texto
	mov di, 0 ;controlar pos pantalla
	
	escribir:  ;comienza el bucle escribir
	                        
		mov al, texto[si] ;movemos a al la posicion si de la cadena, es decir, cargamos en
		                  ;al el caracter a imprimir
		mov ah,color ;color        
		mov es:[di], ax;en el segmento extra movemos la posicion di que nos dice a que posicion va 
		               ;el caracter
		               ;Esto lo que hace es imprimir por pantalla 
		inc si       ;incrementamos si en uno tras imprimir un caracter
		add di, 2    ;sumamos 2 a di porque en modo texto cada caracter ocupa dos posiciones    
		cmp di, 0FA0h
		je inicio 
	
	ultdigito:
	                     
	cmp texto[si], '*'  ;comparamos la posicion si con * para comprobar si se ha leido entera la cadena
	; no imprimimos * por pantalla pues lo usamos como referencia para saber cuando ha terminado la cadena 
	je vuelta   ;si es igual a * saltamos a vuelta
	                                                                                  
	jmp escribir ;saltamos a escribir 
	
	inicio:
	     sub di, 0FA0h
             inc color
             mov si, 0
             mov acumulador, 30h
	     jmp ultdigito   
	
	reinicio:
	
	     sub acumulador, 0Ah ;acumulador vuelve a su valor original (30h) que equivale a 0 
	     jmp escribir      
		
	vuelta: 
	
	    mov si, 0      ;volvemos a poner si a 0 para volver a escribir la cadena
	    mov al, acumulador ;movemos a al el acumulador, es decir, cargamos en al el valor del
	                       ;caracter del codigo ASCII a imprimir por pantalla
	                       
	    mov ah, color    ;color                                     
	    mov es:[di], ax ;movemos a es el caracter a mostrar por pantalla y la posicion donde tiene que ir
	    inc acumulador ;incrementamos el acumulador 
	    add di, 2  ;sumamos 2 a di
	    cmp acumulador, 3Ah ; valor siguiente al numero 9 
	    je reinicio   
	    jmp escribir ;saltamos a escribir      
	        
	mov ah, 0 ;interrupcion
	int 16h

	mov ah, 4Ch ;devolvemos el control al sistema operativo
	int 21h  
	
end