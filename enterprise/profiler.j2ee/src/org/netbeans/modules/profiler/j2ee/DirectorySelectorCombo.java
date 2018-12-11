/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.profiler.j2ee;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.openide.util.NbBundle;

/**
 *
 * @author  Jaroslav Bachorik
 */
@NbBundle.Messages({
    "DirectorySelectorCombo_SelectActionString=Select action",
    "DirectorySelectorCombo_NoneString=<None>",
    "DirectorySelectorCombo_ActionString=Action...",
    "DirectorySelectorCombo_DialogCaption=Browse",
    "DirectorySelectorCombo_DialogFilter=Supported files"
})
public class DirectorySelectorCombo extends javax.swing.JPanel {
  public static final String PROPERTY_SELECTEDPATH = "selectedPath"; // NOI18N
  
  // <editor-fold defaultstate="collapsed" desc="Inner classes">
  private class SeparatedListCellRenderer implements ListCellRenderer {
    private ListCellRenderer delegate;
    
    public SeparatedListCellRenderer() {
      delegate = new DefaultListCellRenderer();
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      if (value instanceof JSeparator)
        return (Component)value;
      
      return delegate.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
  }
  
  private abstract class ComboListElement {
    private Object value;
    private boolean asVolatile;
    
    public abstract void onSelection();
    
    public ComboListElement(Object object) {
      this(object, false);
    }
    
    public ComboListElement(Object object, boolean asVolatile) {
      value = object;
      this.asVolatile = asVolatile;
    }
    
    public void select() {
      onSelection();
      String oldVal = (propertyChangeTracker != null ? propertyChangeTracker.toString() : ""); // NOI18N
      String newVal = isValidSelection() ? (lastSelectedObject != null ? lastSelectedObject.toString() : "") : null; // NOI18N
      firePropertyChange(PROPERTY_SELECTEDPATH, oldVal, newVal);
      propertyChangeTracker = newVal;
    }
    public Object getValue() {
      return value;
    }
    public boolean isVolatile() {
      return asVolatile;
    }
    public String toString() {
      return value != null ? value.toString() : ""; // NOI18N
    }
    public boolean equals(Object other) {
      if (!(other instanceof ComboListElement))
        return false;
      if (value == null || other == null || ((ComboListElement)other).getValue() == null)
        return false;
      
      return (value.equals(((ComboListElement)other).getValue()));
    }
  }
  
  private class StringComboListElement extends ComboListElement {
    public StringComboListElement(Object object) {
      super(object, false);
    }
    
    public StringComboListElement(Object object, boolean asVolatile) {
      super(object, asVolatile);
    }
    
    public void onSelection() {
      lastSelectedObject = this;
      validSelection = true;
      if (lastSelectedObject != null) {
        fileMRU.setToolTipText(lastSelectedObject.toString());
      }
    };
  }
  // </editor-fold>
  
  private Object lastSelectedObject = null, propertyChangeTracker = null;
  private int lastKeypress = 0;
  
  private String actionText, welcomeText, noneText;
  private int itemCountLimit;
  private boolean showWelcome;
  private boolean validSelection;
  private String lastSelectedPath;
  private Set<String> supportedExtensions;
  
  /** Creates new form DirectorySelectorCombo */
  public DirectorySelectorCombo() {
    initComponents();
    initCustom();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileMRU = new javax.swing.JComboBox();

        fileMRU.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fileMRU.setRenderer(new SeparatedListCellRenderer());
        fileMRU.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                fileMRUPopupMenuWillBecomeVisible(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                fileMRUPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        fileMRU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMRUActionPerformed(evt);
            }
        });
        fileMRU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fileMRUKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileMRU, 0, 207, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileMRU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        fileMRU.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DirectorySelectorCombo.class, "DirectorySelectorCombo.fileMRU.AccessibleContext.accessibleName")); // NOI18N
        fileMRU.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DirectorySelectorCombo.class, "DirectorySelectorCombo.fileMRU.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
  
  private void fileMRUPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fileMRUPopupMenuWillBecomeVisible
    DefaultComboBoxModel model = (DefaultComboBoxModel)fileMRU.getModel();
    Collection mukls = new ArrayList();
    for(int i=0;i<model.getSize();i++) {
      if (!(model.getElementAt(i) instanceof ComboListElement))
        continue;
      if (((ComboListElement)model.getElementAt(i)).isVolatile())
        mukls.add(model.getElementAt(i));
    }
    for (Iterator it = mukls.iterator(); it.hasNext();) {
      Object elem = (Object) it.next();
      model.removeElement(elem);
      it.remove();
    }
  }//GEN-LAST:event_fileMRUPopupMenuWillBecomeVisible
  
  private void fileMRUPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fileMRUPopupMenuWillBecomeInvisible
    if (!(fileMRU.getSelectedItem() instanceof ComboListElement))
      return;
    
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ((ComboListElement)fileMRU.getSelectedItem()).select();
      }
    });
  }//GEN-LAST:event_fileMRUPopupMenuWillBecomeInvisible
  
  private void fileMRUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fileMRUKeyPressed
    switch(evt.getKeyCode()) {
      case KeyEvent.VK_UP:
        lastKeypress = 1;
        break;
      case KeyEvent.VK_DOWN:
        lastKeypress = 2;
        break;
      default:
        lastKeypress = 0;
    }
  }//GEN-LAST:event_fileMRUKeyPressed
  
  private void fileMRUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMRUActionPerformed
    if(fileMRU.getSelectedItem() == null)
      return;
    
    if (fileMRU.getSelectedItem() instanceof JSeparator) {
      int currentIndex = fileMRU.getSelectedIndex();
      if (lastKeypress == 1) {
        currentIndex--;
      } else if (lastKeypress == 2) {
        currentIndex++;
      }
      if (currentIndex < 0) {
        currentIndex = fileMRU.getItemCount() - 1;
      } else if (currentIndex >= fileMRU.getItemCount()) {
        currentIndex = 0;
      }
      
      fileMRU.setSelectedIndex(currentIndex);
    }
  }//GEN-LAST:event_fileMRUActionPerformed
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox fileMRU;
    // End of variables declaration//GEN-END:variables
  
  
  private void initCustom() {
    showWelcome = true;
    welcomeText = Bundle.DirectorySelectorCombo_SelectActionString(); 
    noneText = Bundle.DirectorySelectorCombo_NoneString(); 
    actionText = Bundle.DirectorySelectorCombo_ActionString(); 
    itemCountLimit = 2;
    supportedExtensions = new HashSet<String>();
  }
  
  private void changeModel() {
    DefaultComboBoxModel model = (DefaultComboBoxModel)fileMRU.getModel();
    model.removeAllElements();
    if (isShowWelcome()) {
      model.addElement(new StringComboListElement(getWelcomeText(), true));
    }
    model.addElement(new ComboListElement(getNoneText()) {
      public void onSelection() {
        validSelection = false;
      }
    });
    JSeparator separ = new JSeparator();
    model.addElement(separ);
    model.addElement(new ComboListElement(getActionText()) {
      public void onSelection() {
        validSelection = false;
        browseFiles();
      }
    });
  }
  
  private void browseFiles() {
    final JFileChooser chooser = new JFileChooser();
    
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
    chooser.setDialogType(JFileChooser.OPEN_DIALOG);
    chooser.setDialogTitle(Bundle.DirectorySelectorCombo_DialogCaption());
    chooser.setSelectedFile(new File(lastSelectedPath));
    chooser.setFileFilter(new FileFilter() {
      public boolean accept(File f) {
        if (f.isDirectory())
          return true;
        String path = f.getAbsolutePath();
        String ext = path.substring(path.lastIndexOf('.') + 1); // NOI18N
        return supportedExtensions.contains(ext);
      }
      public String getDescription() {
        return Bundle.DirectorySelectorCombo_DialogFilter();
      }
    });
    final int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      final File dir = chooser.getSelectedFile();
      ComboListElement newPath = addPath(dir.getAbsolutePath());
      fileMRU.setSelectedItem(newPath);
      newPath.select();
    } else if (returnVal == JFileChooser.CANCEL_OPTION) {
      fileMRU.setSelectedItem(lastSelectedObject);
    }
  }
  
  private ComboListElement addPath(String path) {
    DefaultComboBoxModel model = (DefaultComboBoxModel)fileMRU.getModel();
    ComboListElement newPath = new StringComboListElement(path);
    int index = model.getIndexOf(newPath);
    if (index == -1) {
      model.insertElementAt(newPath, 1);
    }
    if (model.getSize() > itemCountLimit + 3) {
      model.removeElementAt(model.getSize() - 3);
    }
    return newPath;
  }
  
  public String getActionText() {
    return actionText;
  }
  public void setActionText(String value) {
    actionText = value;
    changeModel();
  }
  
  public String getWelcomeText() {
    return welcomeText;
  }
  public void setWelcomeText(String value) {
    welcomeText = value;
    changeModel();
  }
  
  public String getNoneText() {
    return noneText;
  }
  public void setNoneText(String value) {
    noneText = value;
    changeModel();
  }
  
  public int getItemCountLimit() {
    return itemCountLimit;
  }
  public void setItemCountLimit(int value) {
    if (value < 2) {
      value = 2;
    }
    itemCountLimit = value;
  }
  
  public boolean isShowWelcome() {
    return showWelcome;
  }
  public void setShowWelcome(boolean value) {
    showWelcome = value;
  }
  
  public boolean isEditable() {
    return fileMRU.isEditable();
  }
  public void setEditable(boolean value) {
    fileMRU.setEditable(value);
  }
  
  public boolean isValidSelection() {
    return validSelection;
  }

  public void setSupportedExtensions(Set<String> supportedExtensions) {
    this.supportedExtensions.clear();
    this.supportedExtensions.addAll(supportedExtensions);
  }
  
  public Set<String> getSupportedExtensions() {
    return supportedExtensions;
  }
  
  public String getSelectedPath() {
    if (isValidSelection()) {
      lastSelectedPath = fileMRU.getSelectedItem().toString();
      return fileMRU.getSelectedItem().toString();
    } else {
      return ""; // TODO throw an exception
    }
  }
  
  public void setStartDir(final String path) {
    lastSelectedPath = path;
  }
  
  public String getStartDir() {
    return lastSelectedPath;
  }
}
