/* 
 * Copyright (C) 2018 Julian A. Santos 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.juliansantosinfo.jascopyfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * <code>JASDateHour</code> is a library that makes it easy to copy between 
 * files.
 * @author Julian A. Santos
 * @since 22/08/2018
 * @version 0.01
 * @see <a href="https://github.com/juliansantosinfo/JASCopyFile">Github</a>
 */
public class JASCopyFile {

    private File fileFrom;
    private File fileTo;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static FileChannel channelFrom;
    private static FileChannel channelTo;
    
    /**
     * Creates an empty instance of class JASCopyFile.
     */
    public JASCopyFile() {
    }
    
    /**
     * Creates an instance of the class JASCopyFile defining source and 
     * destination file.
     * 
     * @param fileFrom
     * @param fileTo 
     */
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
    
    /**
     * Copy file using as source and target the objects of type File defined in 
     * the class instacia.
     */
    public boolean copy() {
        
        boolean copied = false;
        
        try {
            fileInputStream = new FileInputStream(fileFrom);
            fileOutputStream = new FileOutputStream(fileTo);
            channelFrom = fileInputStream.getChannel();
            channelTo = fileOutputStream.getChannel();
            channelTo.transferFrom(channelFrom, 0, channelTo.size());
            channelFrom.close();
            channelTo.close();
            copied = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(JASCopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return copied;
    }
    
    /**
     * Copy file receiving as parameter an object of type File for source and 
     * destination. If file does not exist window prompts for source and 
     * destination file.
     * 
     * @param fileFrom
     * @param fileTo 
     */
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
            copied = true;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(JASCopyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return copied;
    }
    
    // Getters and Setters.
    // ------------------------------------------------------------------------
    /**
     * Gets the source file to be copied.
     * 
     * @return source file.
     */
    public File getFileFrom() {
        return fileFrom;
    }
    
    /**
     * Sets source file to be copied.
     * 
     * @param fileFrom 
     */
    public void setFileFrom(File fileFrom) {
        this.fileFrom = fileFrom;
    }
    
    /**
     * Gets the file destination.
     * 
     * @return file destination.
     */
    public File getFileTo() {
        return fileTo;
    }
    
    /**
     * Sets sourcefile destination.
     * 
     * @param fileTo 
     */
    public void setFileTo(File fileTo) {
        this.fileTo = fileTo;
    }
    
}
