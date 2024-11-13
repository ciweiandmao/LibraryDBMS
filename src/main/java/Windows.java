import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Windows extends JFrame{


    public static boolean isChanging=false;

    public static String Target="";

    public static Object MyData;

    static String url = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
    static String username = "root";
    static String password = "9999";

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    Administer administer = new Administer();

    public Windows() throws SQLException {
        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000,800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel)getContentPane();
        imPanel.setOpaque(false);


        JButton LOGIN=new JButton("登录");
        JButton DISPOSE=new JButton("退出系统");
        JLabel label=new JLabel("图书馆管理系统");
        Font font=new Font("宋体",Font.BOLD,40);
        label.setFont(font);
        JLabel L1=new JLabel("账号");
        JLabel L2=new JLabel("密码");
        JTextField T1=new JTextField();
        JTextField T2=new JPasswordField();



        LOGIN.setBounds(450,500,70,30);
        DISPOSE.setBounds(800,680,100,30);
        label.setBounds(340,100,300,70);
        L1.setBounds(250,250,50,30);
        L2.setBounds(250,350,50,30);
        T1.setBounds(320,250,320,30);
        T2.setBounds(320,350,320,30);


        add(LOGIN);
        add(DISPOSE);
        add(label);
        add(L1);add(L2);
        add(T1);add(T2);


        JButton bu =new JButton(" ");
        bu.setContentAreaFilled(false);		//按键透明
        bu.setBorderPainted(false);		//边框透明
        bu.setEnabled(false);
        add(bu);

        DISPOSE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Windows.this.dispose();
            }
        });
        T1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER)
                    T2.grabFocus();}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        T2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    LOGIN.doClick();}}
            @Override
            public void keyReleased(KeyEvent e) {

              }});
        LOGIN.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (login(T1.getText(),T2.getText()))
                        {
                            JOptionPane.showMessageDialog(null, "管理员登录成功！");
                            JFrame administer1 = new Administer();
                            administer1.setTitle("图书馆管理员界面");
                            administer1.setSize(1000, 800);
                            administer1.setLocation(300, 100);
                            administer1.setResizable(false);
                            administer1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            administer1.setVisible(true);
                            Windows.this.dispose();
                        }
                        else JOptionPane.showMessageDialog(null,"账号或密码错误！");
                    }
                });



        final Toolkit TAKE5= Toolkit.getDefaultToolkit();//按ESC退出的代码
        TAKE5.addAWTEventListener(a -> {
            if (a.getID() == KeyEvent.KEY_PRESSED) {
                KeyEvent evt = (KeyEvent) a;
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    DISPOSE.doClick();
                }}},AWTEvent.KEY_EVENT_MASK);

        T1.addKeyListener(new KeyListener(){//阻止用户输入空格
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_SPACE)
                    T1.setEditable(false);
            }
            public void keyReleased(KeyEvent e) {
                    T1.setEditable(true);
            }
            public void keyTyped(KeyEvent e) {
            }});

        T2.addKeyListener(new KeyListener(){//阻止用户输入空格
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==KeyEvent.VK_SPACE)
                    T2.setEditable(false);
            }
            public void keyReleased(KeyEvent e) {
                T2.setEditable(true);
            }
            public void keyTyped(KeyEvent e) {
            }});

    }

    public static void main(String[] args) throws SQLException {

        JFrame frame = new Windows();

        frame.setTitle("图书馆管理系统");
        frame.setSize(1000, 800);
        frame.setLocation(300, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


        boolean flag = false;
    public boolean login(String username,String password) {
        if(username.equals("") && password.equals(""))
        {
            flag=true;
        }
        else {flag=false;}
        return flag;}



    public static String GetCode(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedNow = now.format(formatter);

        return formattedNow;
    }



    public static String GetTime(int month) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime delayedTime = now.plusMonths(month); // 将时间推迟一个月
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm:ss");
        String formattedDelayedTime = delayedTime.format(formatter);

        return formattedDelayedTime;
    }


    public static void AddNewData(String AddText)//增加新数据
    {

        try {
            PreparedStatement statement = connection.prepareStatement(AddText);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"新建成功");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "新增失败");
        }
    }


    public static void DeleteData(String deleteText) {
        try {
            PreparedStatement statement = connection.prepareStatement(deleteText);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "删除成功");
            } else {
                JOptionPane.showMessageDialog(null, "删除失败");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "删除失败");
        }
    }

    //查找单条元组数据
    public static ResultSet SearchSingle(String SearchText) {
        ResultSet result = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SearchText);
            result = statement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return result;
    }

    //查找一个表某列的所有数据
    public static String[] ColumnSearch(String table,String column_name) {
        String[] dataArray = new String[0];
        try {
            PreparedStatement statement = connection.prepareStatement("select * from "+table);
            ResultSet result = statement.executeQuery();
            List<String> dataList = new ArrayList<>();

            while (result.next()) {
                String value = result.getString(column_name); // 替换为实际的列名
                dataList.add(value);
            }
            // 将数据填入数组
            dataArray = dataList.toArray(new String[0]);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return dataArray;
    }



    public static void ChangeData(String ChangeText)
    {

        try {
            PreparedStatement statement = connection.prepareStatement(ChangeText);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"修改成功");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }


    public static void SearchTable(JTable resultTable,String SearchText)
    {
        try {

            PreparedStatement statement = connection.prepareStatement(SearchText);
            ResultSet StudentResult = statement.executeQuery();
            ShowResult(StudentResult,resultTable);

        } catch (SQLException ex) {
            ex.printStackTrace();
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("SQLError");
            tableModel.addRow(new Object[]{ex.getMessage()});
            resultTable.setModel(tableModel);
        }
    }


    private static void ShowResult(ResultSet resultSet, JTable resultTable) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();   //接收对象中的元组数据
        int columnCount = metaData.getColumnCount();            //计算表格要显示的列数
        DefaultTableModel tableModel = new DefaultTableModel(); //创建一个表格的模式用于存储数据
        for (int i = 1; i <= columnCount; i++) {
            tableModel.addColumn(metaData.getColumnLabel(i));   //添加列名操作
        }
        tableModel.addColumn("修改");
        tableModel.addColumn("删除");
        while (resultSet.next()) {                              //遍历每个元组添加到表格中
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            tableModel.addRow(rowData);
        }
        resultTable.setModel(tableModel);   //设置表格模式

        resultTable.getColumnModel().getColumn(columnCount).setCellRenderer(new TableCellRendererButton("修改"));
        resultTable.getColumnModel().getColumn(columnCount).setCellEditor(new TableCellEditorButton(1));
        resultTable.getColumnModel().getColumn(columnCount+1).setCellRenderer(new TableCellRendererButton("删除"));
        resultTable.getColumnModel().getColumn(columnCount+1).setCellEditor(new TableCellEditorButton(2));


    }


    static class TableCellRendererButton implements TableCellRenderer {
        private String buttonText;

        public TableCellRendererButton(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton(buttonText);
            return button;
        }
    }

    static class TableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        public Object rowData; // 保存按钮所在行的第一列数据

        public TableCellEditorButton(int option) {
            super(new JTextField());
            // 设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            if (option == 1) {
                btn = new JButton("修改");
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isChanging=true;
                        try {
                            CommonTable.DataPage(Target);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } else if (option == 2) {
                btn = new JButton("删除");
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String DeleteText;
                        switch (Target) {
                            case "STU":
                                DeleteText = "DELETE FROM student WHERE STUID = '" + rowData+"'";
                                break;
                            case "BL":
                                DeleteText = "DELETE FROM bookloan WHERE BLID = '" + rowData+"'";
                                break;
                            case "BR":
                                DeleteText = "DELETE FROM bookreturn WHERE BRID = '" + rowData+"'";
                                break;
                            case "DU":
                                DeleteText = "DELETE FROM deviceuse WHERE DUID = '" + rowData+"'";
                                break;
                            case "DEV":
                                DeleteText = "DELETE FROM device WHERE DEVID = '" + rowData+"'";
                                break;
                            case "SPAO":
                                DeleteText = "DELETE FROM spaceorder WHERE SPAOID = '" + rowData+"'";
                                break;
                            case "SPA":
                                DeleteText = "DELETE FROM space WHERE SPAID = '" + rowData+"'";
                                break;
                            case "BO":
                                DeleteText = "DELETE FROM book WHERE BOID = '" + rowData+"'";
                                break;
                            case "BOS":
                                DeleteText = "DELETE FROM bookshelf WHERE BOSID = '" + rowData+"'";
                                break;
                            case "BOI":
                                DeleteText = "DELETE FROM bookimport WHERE BOIID = '" + rowData+"'";
                                break;
                            case "MAN":
                                DeleteText = "DELETE FROM manager WHERE MANID = '" + rowData+"'";
                                break;
                            case "PC":
                                DeleteText = "DELETE FROM purchasechecklist WHERE PCID = '" + rowData+"'";
                                break;
                            case "SUP":
                                DeleteText = "DELETE FROM supplier WHERE SUPID = '" + rowData+"'";
                                break;
                            default:
                                DeleteText = "";
                                break;
                        }
                        DeleteData(DeleteText);
                        CommonTable.CT4.setText("");
                        CommonTable.CT1.doClick();
                    }
                });
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            rowData = table.getModel().getValueAt(row, 0); // 保存按钮所在行的第一列数据
            MyData=rowData;
            return btn;
        }
    }}
