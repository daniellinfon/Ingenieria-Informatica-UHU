.data ; Declaracion de variables

A: .word 6,9,4,2,7,6,4,8,1,8
B: .word 1,3,5,4,8,9,4,7,5,6
C: .word 0,0,0,0,0,0,0,0,0,0

.text ; Conjunto de instrucciones

main: addi r2,r0,A  ; Guardamos en r2 la direccion del primer elemento de A
      addi r3,r0,B  ; Guardamos en r3 la direccion del primer elemento de B
      addi r4,r0,C  ; Guardamos en r4 la direccion del primer elemento de C

      ;*** i=0 -> A[0] ***

      lw r5,0(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,0(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 0(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=1 -> A[4] ***

      lw r5,4(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,4(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 4(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=2 -> A[8] ***

      lw r5,8(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,8(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 8(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=3 -> A[12] ***

      lw r5,12(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,12(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 12(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=4 -> A[16] ***

      lw r5,16(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,16(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 16(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=5 -> A[20] ***

      lw r5,20(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,20(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 20(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=6 -> A[24] ***

      lw r5,24(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,24(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 24(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=7 -> A[28] ***

      lw r5,28(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,28(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 28(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)
 
      ;*** i=8 -> A[32] ***

      lw r5,32(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,32(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 32(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)

      ;*** i=9 -> A[36] ***

      lw r5,36(r2)   ; Cargamos en r5 el valor de A(i)
      lw r6,36(r3)   ; Cargamos en r6 el valor de B(i)
      sub r7,r5,r6  ; Restamos el contenido de r5 por el contenido de r6 y lo guardamos en r7
      sw 36(r4),r7   ; Cargamos en C(i) el contenido de r7 (resultado de la resta)
	
      trap 0 