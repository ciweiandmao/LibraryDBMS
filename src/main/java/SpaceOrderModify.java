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

public class SpaceOrderModify extends JFrame {

    public SpaceOrderModify() throws SQLException {

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);



        JLabel NDV1 = new JLabel("预约编号");
        JLabel NDV2 = new JLabel("学生学号");
        JLabel NDV3 = new JLabel("空间编号");
        JLabel NDV4 = new JLabel("预约时间");
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

        NDV11.setText("SPAO" + Windows.GetCode());
        NDV11.setEditable(false);


        if (Windows.isChanging) {
            ResultSet Data = Windows.SearchSingle("select * from spaceorder where SPAOID ='" + Windows.MyData + "'");
            Data.next();
            NDV11.setText(Data.getString("SPAOID"));
            NDV12.setText(Data.getString("StudentID"));
            NDV13.setText(Data.getString("SpaceID"));
            NDV14.setText(Data.getString("OrderTime"));

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
                JFrame SPAO = new CommonTable();
                SPAO.setTitle("空间预约管理");
                SPAO.setSize(1000, 800);
                SPAO.setLocation(300, 100);
                SPAO.setResizable(false);
                SPAO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                SPAO.setVisible(true);
                SpaceOrderModify.this.dispose();}



        });//返回上一级


        NDV9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Windows.isChanging) {
                    Windows.ChangeData("UPDATE spaceorder SET  StudentID = '"+NDV12.getText()+"', SpaceID = '"+NDV13.getText()+"', " +
                            "OrderTime = '"+NDV14.getText()+"' WHERE SPAOID = '"+Windows.MyData+"'");
                } else {
                    Windows.AddNewData("INSERT INTO spaceorder (SPAOID,StudentID,SpaceID,OrderTime) VALUES ('"
                            + NDV11.getText() + "', '" + NDV12.getText() + "','" + NDV13.getText() + "','" + NDV14.getText() +  "')");
                }
                NDV10.doClick();
            }

        });
    }
}
