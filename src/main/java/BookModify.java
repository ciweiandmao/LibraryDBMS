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

public class BookModify extends JFrame {

    public BookModify() throws SQLException {

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);




        JLabel NDV1 = new JLabel("书本编号");
        JLabel NDV2 = new JLabel("书名");
        JLabel NDV3 = new JLabel("书架");
        JLabel NDV4 = new JLabel("ISBN");
        JLabel NDV5 = new JLabel("价格");
        JLabel NDV6 = new JLabel("管理员");
        JButton NDV9 = new JButton("确定");
        JButton NDV10 = new JButton("返回");
        JTextField NDV11 = new JTextField();
        JTextField NDV12 = new JTextField();
        JComboBox NDV13=new JComboBox(Windows.ColumnSearch("bookshelf","BOSID"));
        JTextField NDV14 = new JTextField();
        JTextField NDV15 = new JTextField();
        JComboBox NDV16=new JComboBox(Windows.ColumnSearch("manager","MANID"));


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

        NDV11.setText("BO" + Windows.GetCode());
        NDV11.setEditable(false);


        if (Windows.isChanging) {
            ResultSet Data = Windows.SearchSingle("select * from book where BOID ='" + Windows.MyData + "'");
            Data.next();
            NDV11.setText(Data.getString("BOID"));
            NDV12.setText(Data.getString("Name"));
            NDV13.setSelectedItem(Data.getString("BookshelfID"));
            NDV14.setText(Data.getString("ISBN"));
            NDV15.setText(Data.getString("Price"));
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
                JFrame BO = new CommonTable();
                BO.setTitle("书籍管理");
                BO.setSize(1000, 800);
                BO.setLocation(300, 100);
                BO.setResizable(false);
                BO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BO.setVisible(true);
                BookModify.this.dispose();}



        });//返回上一级
        NDV9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Windows.isChanging) {
                    Windows.ChangeData("UPDATE book SET  Name = '"+NDV12.getText()+"', BookshelfID = '"+NDV13.getSelectedItem()+"', " +
                            "ISBN = '"+NDV14.getText()+"', Price = '"+NDV15.getText()+"', ManagerID = '"+NDV16.getSelectedItem()+"' WHERE BOID = '"+Windows.MyData+"'");
                } else {
                    Windows.AddNewData("INSERT INTO book (BOID,Name,BookshelfID,ISBN,Price,ManagerID) VALUES ('"
                            + NDV11.getText() + "', '" + NDV12.getText() + "','" + NDV13.getSelectedItem() + "','" + NDV14.getText() + "','" + NDV15.getText() + "','" + NDV16.getSelectedItem() + "')");
                }
                NDV10.doClick();
            }

        });


    }
}