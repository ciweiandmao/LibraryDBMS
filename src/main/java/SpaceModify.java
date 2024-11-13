import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaceModify extends JFrame {

    public SpaceModify() throws SQLException {

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        String[] type=new String[]{"座位","自习室"};

        JLabel NDV1 = new JLabel("空间编号");
        JLabel NDV2 = new JLabel("空间类型");
        JLabel NDV3 = new JLabel("空间容量");
        JLabel NDV4 = new JLabel("空间位置");
        JLabel NDV5 = new JLabel("空间情况");
        JLabel NDV6 = new JLabel("管理员");
        JButton NDV9 = new JButton("确定");
        JButton NDV10 = new JButton("返回");
        JTextField NDV11 = new JTextField();
        JComboBox NDV12 = new JComboBox(type);
        JTextField NDV13 = new JTextField();
        JTextField NDV14 = new JTextField();
        JTextField NDV15 = new JTextField();
        JComboBox NDV16 = new JComboBox(Windows.ColumnSearch("manager","MANID"));

        NDV1.setBounds(200, 100, 100, 50);
        NDV2.setBounds(200, 150, 100, 50);
        NDV3.setBounds(200, 200, 100, 50);
        NDV4.setBounds(200, 250, 100, 50);
        NDV5.setBounds(200, 300, 100, 50);
        NDV6.setBounds(200, 350, 100, 50);
        NDV9.setBounds(500, 550, 70, 30);
        NDV10.setBounds(600, 550, 70, 30);
        NDV11.setBounds(300, 110, 300, 30);
        NDV12.setBounds(300, 160, 300, 30);
        NDV13.setBounds(300, 210, 300, 30);
        NDV14.setBounds(300, 260, 300, 30);
        NDV15.setBounds(300, 310, 300, 30);
        NDV16.setBounds(300, 360, 300, 30);

        add(NDV1);
        add(NDV2);
        add(NDV3);
        add(NDV4);
        add(NDV5);
        add(NDV6);
        add(NDV9);
        add(NDV10);
        add(NDV11);
        add(NDV12);
        add(NDV13);
        add(NDV14);
        add(NDV15);
        add(NDV16);

        NDV11.setText("SPA" + Windows.GetCode());
        NDV11.setEditable(false);


        if (Windows.isChanging) {
            ResultSet Data = Windows.SearchSingle("select * from space where SPAID ='" + Windows.MyData + "'");
            Data.next();
            NDV11.setText(Data.getString("SPAID"));
            NDV12.setSelectedItem(Data.getString("SpaceType"));
            NDV13.setText(Data.getString("SpaceCapacity"));
            NDV14.setText(Data.getString("SpaceLocation"));
            NDV15.setText(Data.getString("SpaceStatus"));
            NDV16.setSelectedItem(Data.getString("ManagerID"));
        }

        JButton bu = new JButton("");      //无文字覆盖按钮
        bu.setContentAreaFilled(false);        //按键透明
        bu.setBorderPainted(false);        //边框透明
        bu.setEnabled(false);
        add(bu);



        NDV10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Windows.isChanging=false;
                JFrame SPA = new CommonTable();
                SPA.setTitle("空间管理");
                SPA.setSize(1000, 800);
                SPA.setLocation(300, 100);
                SPA.setResizable(false);
                SPA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                SPA.setVisible(true);
                SpaceModify.this.dispose();}



        });//返回上一级

        NDV9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Windows.isChanging) {
                    Windows.ChangeData("UPDATE space SET  SpaceType = '"+NDV12.getSelectedItem()+"', SpaceCapacity = '"+NDV13.getText()+"', " +
                            "SpaceLocation = '"+NDV14.getText()+"', SpaceStatus = '"+NDV15.getText()+"', ManagerID = '"+NDV16.getSelectedItem()+"' WHERE SPAID = '"+Windows.MyData+"'");
                } else {
                    Windows.AddNewData("INSERT INTO space (SPAID,SpaceType,SpaceCapacity,SpaceLocation,SpaceStatus,ManagerID) VALUES ('"
                            + NDV11.getText() + "', '" + NDV12.getSelectedItem() + "','" + NDV13.getText() + "','" + NDV14.getText() + "','" + NDV15.getText() + "','" + NDV16.getSelectedItem() + "')");
                }
                NDV10.doClick();
            }

        });

    }
}
