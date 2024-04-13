package com.headfirst.universalserver;

import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

public class ServiceBrowser {

    JPanel maiPanel;
    JComboBox serviceList;
    ServiceServer server;

    public void builGUI(){
        JFrame frame = new JFrame("RMI Browser");
        maiPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, maiPanel);

        Object[] services = getServiceList();

        serviceList = new JComboBox<>(services);

        frame.getContentPane().add(BorderLayout.NORTH, serviceList);

        serviceList.addActionListener(new MyListListener());

        frame.setSize(500,500);
        frame.setVisible(true);
    }

    void loadService(Object serviceSelection){
        try{
            Service svc = server.getService(serviceSelection);

            maiPanel.removeAll();
            maiPanel.add(svc.getGUIPanel());
            maiPanel.validate();
            maiPanel.repaint();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    Object[] getServiceList(){
        Object obj = null;
        Object[] services = null;

        try{
            obj = Naming.lookup("rmi://127.0.0.1/ServiceServer");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        server = (ServiceServer) obj;

        try{
            services = server.getServiceList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return services;
    }

    class MyListListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            Object selection = serviceList.getSelectedItem();
            loadService(selection);
        }
    }

    public static void main(String[] args) {
        new ServiceBrowser().builGUI();
    }
    
}
