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

public class SupplierModify extends JFrame {

    public SupplierModify() throws SQLException {

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        JLabel NDV1 = new JLabel("供应商编号");
        JLabel NDV2 = new JLabel("公司");
        JLabel NDV3 = new JLabel("联络人");
        JLabel NDV4 = new JLabel("联系方式");
        JButton NDV9 = new JButton("确定");
        JButton NDV10 = new JButton("返回");
        JTextField NDV11 = new JTextField();
        JTextField NDV12 = new JTextField();
        JTextField NDV13 = new JTextField();
        JTextField NDV14 = new JTextField();


        NDV1.setBounds(200, 100, 100, 50);
        NDV2.setBounds(200, 150, 100, 50);
        NDV3.setBounds(200, 200, 100, 50);
        NDV4.setBounds(200, 250, 100, 50);
        NDV9.setBounds(500, 550, 70, 30);
        NDV10.setBounds(600, 550, 70, 30);
        NDV11.setBounds(300, 110, 300, 30);
        NDV12.setBounds(300, 160, 300, 30);
        NDV13.setBounds(300, 210, 300, 30);
        NDV14.setBounds(300, 260, 300, 30);

        add(NDV1);
        add(NDV2);
        add(NDV3);
        add(NDV4);
        add(NDV9);
        add(NDV10);
        add(NDV11);
        add(NDV12);
        add(NDV13);
        add(NDV14);


        NDV11.setText("SUP" + Windows.GetCode());
        NDV11.setEditable(false);

        if (Windows.isChanging) {
            ResultSet Data = Windows.SearchSingle("select * from supplier where SUPID ='" + Windows.MyData + "'");
            Data.next();
            NDV11.setText(Data.getString("SUPID"));
            NDV12.setText(Data.getString("Company"));
            NDV13.setText(Data.getString("ContactPerson"));
            NDV14.setText(Data.getString("ContactNumber"));

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
                JFrame SUP = new CommonTable();
                SUP.setTitle("供应商管理");
                SUP.setSize(1000, 800);
                SUP.setLocation(300, 100);
                SUP.setResizable(false);
                SUP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                SUP.setVisible(true);
                SupplierModify.this.dispose();}



        });//返回上一级


        NDV9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Windows.isChanging) {
                    Windows.ChangeData("UPDATE supplier SET  Company = '"+NDV12.getText()+"', ContactPerson = '"+NDV13.getText()+"', " +
                            "ContactNumber = '"+NDV14.getText()+"' WHERE SUPID = '"+Windows.MyData+"'");
                } else {
                    Windows.AddNewData("INSERT INTO supplier (SUPID,Company,ContactPerson,ContactNumber) VALUES ('"
                            + NDV11.getText() + "', '" + NDV12.getText() + "','" + NDV13.getText() + "','" + NDV14.getText() +"')");
                }
                NDV10.doClick();
            }

        });


    }
}
