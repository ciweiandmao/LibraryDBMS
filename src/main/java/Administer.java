import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Administer extends JFrame {

    public Administer(){

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);

        JButton ADMI1=new JButton("学生管理");
        JButton ADMI2=new JButton("书籍借出记录");
        JButton ADMI3=new JButton("书籍归还记录");
        JButton ADMI4=new JButton("设备使用记录");
        JButton ADMI5=new JButton("设备管理");
        JButton ADMI6=new JButton("空间预约管理");
        JButton ADMI7=new JButton("空间管理");
        JButton ADMI8=new JButton("书籍管理");
        JButton ADMI9=new JButton("书架管理");
        JButton ADMI10=new JButton("书籍入库单管理");
        JButton ADMI11=new JButton("图书馆管理员");
        JButton ADMI12=new JButton("购书清单管理");
        JButton ADMI13=new JButton("图书馆供应商管理");
        JButton ADMI14=new JButton("退出");

        ADMI1.setBounds(270,50,200,50);
        ADMI2.setBounds(270,150,200,50);
        ADMI3.setBounds(270,250,200,50);
        ADMI4.setBounds(270,350,200,50);
        ADMI5.setBounds(270,450,200,50);
        ADMI6.setBounds(270,550,200,50);
        ADMI7.setBounds(270,650,200,50);
        ADMI8.setBounds(470,50,200,50);
        ADMI9.setBounds(470,150,200,50);
        ADMI10.setBounds(470,250,200,50);
        ADMI11.setBounds(470,350,200,50);
        ADMI12.setBounds(470,450,200,50);
        ADMI13.setBounds(470,550,200,50);
        ADMI14.setBounds(470,650,200,50);

        add(ADMI1);
        add(ADMI2);
        add(ADMI3);
        add(ADMI4);
        add(ADMI5);
        add(ADMI6);
        add(ADMI7);
        add(ADMI8);
        add(ADMI9);
        add(ADMI10);
        add(ADMI11);
        add(ADMI12);
        add(ADMI13);
        add(ADMI14);


        JButton bu =new JButton(" ");
        bu.setContentAreaFilled(false);		//按键透明
        bu.setBorderPainted(false);		//边框透明
        bu.setEnabled(false);
        add(bu);


        ADMI1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("学生管理","STU");

            }
        });

        ADMI2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("书籍借出记录管理","BL");

            }
        });

        ADMI3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("书籍归还记录管理","BR");


            }
        });

        ADMI4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("设备使用记录管理","DU");


            }
        });

        ADMI5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("设备管理","DEV");

            }
        });

        ADMI6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("空间预约管理","SPAO");

            }
        });

        ADMI7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("空间管理","SPA");


            }
        });

        ADMI8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("书籍管理","BO");

            }
        });

        ADMI9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("书架管理","BOS");

            }
        });
        ADMI10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("书籍入库单管理","BOI");
            }
        });
        ADMI11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("管理员管理","MAN");

            }
        });
        ADMI12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("购书清单管理","PC");

            }
        });
        ADMI13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Access("供应商管理","SUP");

            }
        });

        ADMI14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"管理员退出成功!");
                JFrame frame = null;
                try {
                    frame = new Windows();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.setTitle("云平台制造管理系统");
                frame.setSize(1000, 800);
                frame.setLocation(300, 100);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                Administer.this.dispose();

            }
        });


    }

    public void Access(String title,String target)
    {
        Windows.Target=target;
        JFrame CT = new CommonTable();
        CT.setTitle(title);
        CT.setSize(1000, 800);
        CT.setLocation(300, 100);
        CT.setResizable(false);
        CT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CT.setVisible(true);
        Administer.this.dispose();
    }


}
