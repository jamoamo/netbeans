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
package org.netbeans.modules.web.clientproject.ui.wizard;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.event.ChangeListener;
import org.openide.awt.Mnemonics;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;

public class Tools extends JPanel {

    private final ChangeSupport changeSupport = new ChangeSupport(this);


    public Tools() {
        assert EventQueue.isDispatchThread();
        initComponents();
        init();
    }

    private void init() {
        ItemListener defaultItemListener = new DefaultItemListener();
        packageJsonCheckBox.addItemListener(defaultItemListener);
        bowerJsonCheckBox.addItemListener(defaultItemListener);
        gruntfileCheckBox.addItemListener(defaultItemListener);
        gulpfileCheckBox.addItemListener(defaultItemListener);
    }

    public void addChangeListener(ChangeListener listener) {
        changeSupport.addChangeListener(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeSupport.removeChangeListener(listener);
    }

    public boolean isNpmEnabled() {
        return packageJsonCheckBox.isSelected();
    }

    public void setNpmEnabled(boolean enabled) {
        packageJsonCheckBox.setSelected(enabled);
    }

    public boolean isBowerEnabled() {
        return bowerJsonCheckBox.isSelected();
    }

    public void setBowerEnabled(boolean enabled) {
        bowerJsonCheckBox.setSelected(enabled);
    }

    public boolean isGruntEnabled() {
        return gruntfileCheckBox.isSelected();
    }

    public void setGruntEnabled(boolean enabled) {
        gruntfileCheckBox.setSelected(enabled);
    }

    public boolean isGulpEnabled() {
        return gulpfileCheckBox.isSelected();
    }

    public void setGulpEnabled(boolean enabled) {
        gulpfileCheckBox.setSelected(enabled);
    }

    void fireChange() {
        changeSupport.fireChange();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form
     * Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        packageJsonCheckBox = new JCheckBox();
        bowerJsonCheckBox = new JCheckBox();
        gruntfileCheckBox = new JCheckBox();
        gulpfileCheckBox = new JCheckBox();

        packageJsonCheckBox.setSelected(true);
        Mnemonics.setLocalizedText(packageJsonCheckBox, NbBundle.getMessage(Tools.class, "Tools.packageJsonCheckBox.text")); // NOI18N

        bowerJsonCheckBox.setSelected(true);
        Mnemonics.setLocalizedText(bowerJsonCheckBox, NbBundle.getMessage(Tools.class, "Tools.bowerJsonCheckBox.text")); // NOI18N

        gruntfileCheckBox.setSelected(true);
        Mnemonics.setLocalizedText(gruntfileCheckBox, NbBundle.getMessage(Tools.class, "Tools.gruntfileCheckBox.text")); // NOI18N

        gulpfileCheckBox.setSelected(true);
        Mnemonics.setLocalizedText(gulpfileCheckBox, NbBundle.getMessage(Tools.class, "Tools.gulpfileCheckBox.text")); // NOI18N

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(packageJsonCheckBox)
            .addComponent(bowerJsonCheckBox)
            .addComponent(gruntfileCheckBox)
            .addComponent(gulpfileCheckBox)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(packageJsonCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bowerJsonCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gruntfileCheckBox)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gulpfileCheckBox))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBox bowerJsonCheckBox;
    private JCheckBox gruntfileCheckBox;
    private JCheckBox gulpfileCheckBox;
    private JCheckBox packageJsonCheckBox;
    // End of variables declaration//GEN-END:variables

    //~ Inner classes

    private final class DefaultItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            fireChange();
        }

    }

}
