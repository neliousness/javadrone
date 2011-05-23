/*
 * DroneConfig.java
 *
 * Created on 17.05.2011, 15:19:40
 */
package com.codeminders.controltower;

import com.codeminders.ardrone.ARDrone;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author normenhansen
 */
public class DroneConfig extends javax.swing.JDialog {

    private ARDrone drone;
    private ControlTower tower;
    private Preferences prefs;

    /** Creates new form DroneConfig */
    public DroneConfig(ControlTower tower, boolean modal) {
        super(tower, modal);
        setLocationRelativeTo(tower);
        this.tower = tower;
        prefs = Preferences.userNodeForPackage(this.getClass());
        initComponents();
        loadSettings();
    }

    private synchronized void saveSettings() {
        prefs.putInt("control:altitude_max", maxAltitude.getValue());
        prefs.putInt("control:euler_angle_max", maxAngle.getValue());
        prefs.putInt("control:control_vz_max", maxSpeed.getValue());
        prefs.putInt("control:control_yaw", maxYaw.getValue());
        prefs.putInt("tower:controller_threshold", controllerDeadzone.getValue());
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            Logger.getLogger(DroneConfig.class.getName()).log(Level.SEVERE, "Cannot save settings: {0}", ex);
        }
    }

    private synchronized void loadSettings() {
        maxAltitude.setValue(prefs.getInt("control:altitude_max", 10000));
        maxAngle.setValue(prefs.getInt("control:euler_angle_max", 20));
        maxSpeed.setValue(prefs.getInt("control:control_vz_max", 2000));
        maxYaw.setValue(prefs.getInt("control:control_yaw", 200));
        controllerDeadzone.setValue(prefs.getInt("tower:controller_threshold", 50));
        updateAlt(null);
        updateAngle(null);
        updateSpeed(null);
        updateYaw(null);
        updateDeadZone(null);
    }

    public synchronized void setDrone(ARDrone drone) {
        this.drone = drone;
    }

    public synchronized void updateDrone() {
        if (drone == null) {
            return;
        }
        try {
            drone.setConfigOption("control:altitude_max", maxAltitude.getValue() + "");
            drone.setConfigOption("control:euler_angle_max", (((float) maxAngle.getValue()) / 100.0f) + "");
            drone.setConfigOption("control:control_vz_max", maxSpeed.getValue() + "");
            drone.setConfigOption("control:control_yaw", (((float) maxAngle.getValue()) / 100.0f) + "");
            tower.setControlThreshold(((float) controllerDeadzone.getValue()) / 100.0f);
        } catch (IOException ex) {
            Logger.getLogger(DroneConfig.class.getName()).log(Level.SEVERE, "Exception Setting data: {0}", ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maxAltitudeLabel = new javax.swing.JLabel();
        maxAltitude = new javax.swing.JSlider();
        maxAngleLabel = new javax.swing.JLabel();
        maxAngle = new javax.swing.JSlider();
        maxSpeedLabel = new javax.swing.JLabel();
        maxSpeed = new javax.swing.JSlider();
        maxYawLabel = new javax.swing.JLabel();
        maxYaw = new javax.swing.JSlider();
        controllerDeadzoneLabel = new javax.swing.JLabel();
        controllerDeadzone = new javax.swing.JSlider();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuration");
        getContentPane().setLayout(new java.awt.GridLayout(6, 2));

        maxAltitudeLabel.setText("Max Altitude");
        getContentPane().add(maxAltitudeLabel);

        maxAltitude.setMaximum(10000);
        maxAltitude.setValue(10000);
        maxAltitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateAlt(evt);
            }
        });
        getContentPane().add(maxAltitude);

        maxAngleLabel.setText("Max Angle");
        getContentPane().add(maxAngleLabel);

        maxAngle.setValue(20);
        maxAngle.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateAngle(evt);
            }
        });
        getContentPane().add(maxAngle);

        maxSpeedLabel.setText("Max Speed");
        maxSpeedLabel.setEnabled(false);
        getContentPane().add(maxSpeedLabel);

        maxSpeed.setMaximum(3000);
        maxSpeed.setValue(2000);
        maxSpeed.setEnabled(false);
        maxSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateSpeed(evt);
            }
        });
        getContentPane().add(maxSpeed);

        maxYawLabel.setText("Rotation Speed");
        getContentPane().add(maxYawLabel);

        maxYaw.setMaximum(611);
        maxYaw.setMinimum(70);
        maxYaw.setValue(200);
        maxYaw.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateYaw(evt);
            }
        });
        getContentPane().add(maxYaw);

        controllerDeadzoneLabel.setText("Controller Deadzone");
        getContentPane().add(controllerDeadzoneLabel);

        controllerDeadzone.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateDeadZone(evt);
            }
        });
        getContentPane().add(controllerDeadzone);

        cancelButton.setText("Close");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);

        okButton.setText("Apply");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        getContentPane().add(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        updateDrone();
        saveSettings();
    }//GEN-LAST:event_okButtonActionPerformed

    private void updateAlt(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updateAlt
        float value = (float) maxAltitude.getValue() / 1000.0f;
        maxAltitude.setToolTipText(value + "m");
        maxAltitudeLabel.setText("Max Altitude (" + value + "m)");
    }//GEN-LAST:event_updateAlt

    private void updateAngle(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updateAngle
        long value = Math.round(Math.toDegrees((float) maxAngle.getValue() / 100.0f));
        maxAngle.setToolTipText(value + "");
        maxAngleLabel.setText("Max Angle (" + value + "°)");
    }//GEN-LAST:event_updateAngle

    private void updateSpeed(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updateSpeed
        float value = (float) maxSpeed.getValue() / 100.0f;
        maxSpeed.setToolTipText(value + "m/s");
        maxSpeedLabel.setText("Max Speed (" + value + "m/s)");
    }//GEN-LAST:event_updateSpeed

    private void updateDeadZone(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updateDeadZone
        float value = (float) controllerDeadzone.getValue();
        controllerDeadzone.setToolTipText(value + "%");
        controllerDeadzoneLabel.setText("Controller Deadzone (" + value + "%)");
    }//GEN-LAST:event_updateDeadZone

    private void updateYaw(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updateYaw
        float value = Math.round(Math.toDegrees((float) maxYaw.getValue() / 100.0f));
        maxYaw.setToolTipText(value + "°/s");
        maxYawLabel.setText("Rotation Speed (" + value + "°/s)");
    }//GEN-LAST:event_updateYaw

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JSlider controllerDeadzone;
    private javax.swing.JLabel controllerDeadzoneLabel;
    private javax.swing.JSlider maxAltitude;
    private javax.swing.JLabel maxAltitudeLabel;
    private javax.swing.JSlider maxAngle;
    private javax.swing.JLabel maxAngleLabel;
    private javax.swing.JSlider maxSpeed;
    private javax.swing.JLabel maxSpeedLabel;
    private javax.swing.JSlider maxYaw;
    private javax.swing.JLabel maxYawLabel;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
