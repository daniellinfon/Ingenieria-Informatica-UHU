/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto3;

/**
 *
 * @author danie
 */
public class Tanque {
    
    private volatile int numLlantas=0, numPC=0;
    
    public synchronized void entrallanta() throws InterruptedException{
        while(numLlantas == 5 || numPC > 1 || (numPC==1 && numLlantas ==3) ){
            wait();
        }
        numLlantas++;
        
    }
    
    public synchronized void salellanta(){
        numLlantas--;
        notifyAll();
    }
    
    public synchronized void entraPC() throws InterruptedException{
        while(numLlantas>3 || numPC > 1 || (numPC==1 && numLlantas >0)){
            wait();
        }
        numPC++;
        
    }
    public synchronized void salePC(){
        numPC--;
        notifyAll();
    }
}
