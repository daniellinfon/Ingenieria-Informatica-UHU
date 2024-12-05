.data ; Declaracion de variables

A: .word 6,9,4,2,7,6,4,8,1,8
B: .word 1,3,5,4,8,9,4,7,5,6
C: .word 0,0,0,0,0,0,0,0,0,0

.text ; Conjunto de instrucciones
main: addi r1,r0,10 ; Guardamos en r1 el numero de iteraciones
      addi r2,r0,A  ; Guardamos en r2 la direccion del primer elemento de A
      addi r3,r0,B  ; Guardamos en r3 la direccion del primer elemento de B
      addi r4,r0,C  ; Guardamos en r4 la direccion del primer elemento de C

bucle: lw r5,0(r2)   ; Cargamos en r5 el valor de A(i)
	 lw r6,0(r3)   ; Cargamos en r6 el valor de B(i)
	 sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
	 sw 0(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)
	
	 addi r2,r2,4   ; Sumamos 4 para movernos por A
       addi r3,r3,4   ; Sumamos 4 para movernos por B
       addi r4,r4,4   ; Sumamos 4 para movernos por C
	 subi r1,r1,1  ; Comprobar 10 iteraciones
	 bnez r1,bucle  ; Si r1 no es 0, vuelve a bucle

trap 0 


