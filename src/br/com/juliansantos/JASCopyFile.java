package br.com.juliansantos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Julian A. Santos
 */
public class JASCopyFile {

    private File fileFrom;
    private File fileTo;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static FileChannel channelFrom;
    private static FileChannel channelTo;

    public JASCopyFile() {
    }

    public JASCopyFile(File fileFrom, File fileTo) {

        this.fileFrom = fileFrom;
        this.fileTo = fileTo;

        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOutputStream = new FileOutputStream(fileTo);
            channelFrom = fileInputStream.getChannel();
            channelTo = fileOutputStream.getChannel();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(JASCopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void copy() {
        
        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOutputStream = new FileOutputStream(fileTo);
            channelFrom = fileInputStream.getChannel();
            channelTo = fileOutputStream.getChannel();
            channelTo.transferFrom(channelFrom, 0, channelTo.size());
            channelFrom.close();
            channelTo.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(JASCopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean copy(File fileFrom, File fileTo) {
        
        boolean copied = false;
        
        if (!fileFrom.exists()) {
            JOptionPane.showMessageDialog(null, "Impossivel ler arquivo de origem em: " + fileFrom.getAbsoluteFile().toString(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
        if (!fileTo.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Impossivel criar arquivo de destino em: " + fileTo.getAbsoluteFile().toString(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        
        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOutputStream = new FileOutputStream(fileTo);
            channelFrom = fileInputStream.getChannel();
            channelTo = fileOutputStream.getChannel();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(JASCopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return copied;
    }

    public File getFileFrom() {
        return fileFrom;
    }

    public void setFileFrom(File fileFrom) {
        this.fileFrom = fileFrom;
    }

    public File getFileTo() {
        return fileTo;
    }

    public void setFileTo(File fileTo) {
        this.fileTo = fileTo;
    }
    
}
