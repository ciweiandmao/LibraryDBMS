import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Objects;

public class CommonTable extends JFrame {

    public static JTextField CT4 = new JTextField();
    public static JButton CT1 = new JButton("全局模糊查询");

    public static JButton Close =new JButton();
    //设置该文本框类内公开
    public CommonTable() {

        ImageIcon BackGround = new ImageIcon("YPT.png");
        JLabel LB = new JLabel(BackGround);//往一个标签中加入图片
        LB.setBounds(0, 0, 1000, 800);
        this.getLayeredPane().add(LB, Integer.valueOf(Integer.MIN_VALUE));//标签添加到第二层面板
        JPanel imPanel = (JPanel) getContentPane();
        imPanel.setOpaque(false);


        JButton CT2 = new JButton("新建");
        JButton CT3 = new JButton("返回");




        CT1.setBounds(450, 100, 150, 30);
        CT2.setBounds(700, 100, 100, 30);
        CT3.setBounds(800, 670, 100, 30);
        CT4.setBounds(100, 100, 300, 30);
        Close.setBounds(1,1,1,1);


        add(CT1);
        add(CT2);
        add(CT3);
        add(CT4);
        add(Close);


        // 创建显示结果的表格
        JTable resultTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(50, 200, 850, 400);
        add(scrollPane);


        JButton bu = new JButton("");      //无文字覆盖按钮
        bu.setContentAreaFilled(false);        //按键透明
        bu.setBorderPainted(false);        //边框透明
        bu.setEnabled(false);
        add(bu);




        CT1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String SearchText=SQLtxt(Windows.Target);
                //System.out.println(SearchText);
                Windows.SearchTable(resultTable,SearchText);
            }});//查询

        CT4.setText("");
        CT1.doClick();//进入界面自动刷新

        CT2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Windows.isChanging=false;
                try {
                    DataPage(Windows.Target);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }});//新建

        CT3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame administer1 = new Administer();
                administer1.setTitle("图书馆管理员界面");
                administer1.setSize(1000, 800);
                administer1.setLocation(300, 100);
                administer1.setResizable(false);
                administer1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                administer1.setVisible(true);
                CommonTable.this.dispose();
            }
        });//返回主菜单

        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonTable.this.dispose();
            }
        });

    }
    public String SQLtxt(String Target) {
        String SearchText = null;
        if (Objects.equals(Target, "STU")) {
            SearchText = "select * from student";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (STUID like '" + CT4.getText() + "')" +
                        " or (Name like '" + CT4.getText() + "')" +
                        " or (Gender like '" + CT4.getText() + "')" +
                        " or (EnrollmentDate like '" + CT4.getText() + "')" +
                        " or (Major like '" + CT4.getText() + "')" +
                        " or (Class like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "BO")) {
            SearchText = "select * from book";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (BOID like '" + CT4.getText() + "')" +
                        " or (Name like '" + CT4.getText() + "')" +
                        " or (BookshelfID like '" + CT4.getText() + "')" +
                        " or (ISBN like '" + CT4.getText() + "')" +
                        " or (Price like '" + CT4.getText() + "')" +
                        " or (ManagerID like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "BOI")) {
            SearchText = "select * from bookimport";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (BOIID like '" + CT4.getText() + "')" +
                        " or (BookID like '" + CT4.getText() + "')" +
                        " or (PurchaseChecklistID like '" + CT4.getText() + "')" +
                        " or (ImportDate like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "BL")) {
            SearchText = "select * from bookloan";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (BLID like '" + CT4.getText() + "')" +
                        " or (StudentID like '" + CT4.getText() + "')" +
                        " or (BookID like '" + CT4.getText() + "')" +
                        " or (BorrowedDate like '" + CT4.getText() + "')" +
                        " or (DeadLine like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "BR")) {
            SearchText = "select * from bookreturn";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (BRID like '" + CT4.getText() + "')" +
                        " or (StudentID like '" + CT4.getText() + "')" +
                        " or (BookID like '" + CT4.getText() + "')" +
                        " or (ReturnDate like '" + CT4.getText() + "')" +
                        " or (BorrowStatus like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "BOS")) {
            SearchText = "select * from bookshelf";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (BOSID like '" + CT4.getText() + "')" +
                        " or (Location like '" + CT4.getText() + "')" +
                        " or (Category like '" + CT4.getText() + "')" +
                        " or (BookCount like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "DEV")) {
            SearchText = "select * from device";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (DEVID like '" + CT4.getText() + "')" +
                        " or (DeviceType like '" + CT4.getText() + "')" +
                        " or (Location like '" + CT4.getText() + "')" +
                        " or (Status like '" + CT4.getText() + "')" +
                        " or (InstallationDate like '" + CT4.getText() + "')" +
                        " or (ManagerID like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "DU")) {
            SearchText = "select * from deviceuse";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (DUID like '" + CT4.getText() + "')" +
                        " or (StudentID like '" + CT4.getText() + "')" +
                        " or (DeviceID like '" + CT4.getText() + "')" +
                        " or (UsageDate like '" + CT4.getText() + "')" +
                        " or (UsageStatus like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "MAN")) {
            SearchText = "select * from manager";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (MANID like '" + CT4.getText() + "')" +
                        " or (Name like '" + CT4.getText() + "')" +
                        " or (Age like '" + CT4.getText() + "')" +
                        " or (Work like '" + CT4.getText() + "')" +
                        " or (Salary like '" + CT4.getText() + "')" +
                        " or (HireDate like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "PC")) {
            SearchText = "select * from purchasechecklist";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (PCID like '" + CT4.getText() + "')" +
                        " or (ManagerID like '" + CT4.getText() + "')" +
                        " or (SupplierID like '" + CT4.getText() + "')" +
                        " or (Number like '" + CT4.getText() + "')" +
                        " or (Price like '" + CT4.getText() + "')" +
                        " or (PurchaseDate like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "SPA")) {
            SearchText = "select * from space";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (SPAID like '" + CT4.getText() + "')" +
                        " or (SpaceType like '" + CT4.getText() + "')" +
                        " or (SpaceCapacity like '" + CT4.getText() + "')" +
                        " or (SpaceLocation like '" + CT4.getText() + "')" +
                        " or (SpaceStatus like '" + CT4.getText() + "')" +
                        " or (ManagerID like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "SPAO")) {
            SearchText = "select * from spaceorder";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (SPAOID like '" + CT4.getText() + "')" +
                        " or (StudentID like '" + CT4.getText() + "')" +
                        " or (SpaceID like '" + CT4.getText() + "')" +
                        " or (OrderTime like '" + CT4.getText() + "')";
            }
        }
        if (Objects.equals(Target, "SUP")) {
            SearchText = "select * from supplier";
            if (!CT4.getText().isEmpty()) {
                SearchText += " where (SUPID like '" + CT4.getText() + "')" +
                        " or (Company like '" + CT4.getText() + "')" +
                        " or (ContactPerson like '" + CT4.getText() + "')" +
                        " or (ContactNumber like '" + CT4.getText() + "')";
            }
        }
        return SearchText;
    }

    public static void DataPage(String Target) throws SQLException {
        JFrame ND = null;
        switch (Target) {
            case "STU":
                ND = new StudentModify();
                ND.setTitle(Windows.isChanging ? "修改学生" : "新建学生");
                break;
            case "BL":
                ND = new BookLoanModify();
                ND.setTitle(Windows.isChanging ? "修改借阅记录" : "新建借阅记录");
                break;
            case "BR":
                ND = new BookReturnModify();
                ND.setTitle(Windows.isChanging ? "修改归还记录" : "新建归还记录");
                break;
            case "DU":
                ND = new DeviceUseModify();
                ND.setTitle(Windows.isChanging ? "修改设备使用记录" : "新建设备使用记录");
                break;
            case "DEV":
                ND = new DeviceModify();
                ND.setTitle(Windows.isChanging ? "修改设备" : "新建设备");
                break;
            case "SPAO":
                ND = new SpaceOrderModify();
                ND.setTitle(Windows.isChanging ? "修改空间预约" : "新建空间预约");
                break;
            case "SPA":
                ND = new SpaceModify();
                ND.setTitle(Windows.isChanging ? "修改空间" : "新建空间");
                break;
            case "BO":
                ND = new BookModify();
                ND.setTitle(Windows.isChanging ? "修改书籍" : "新建书籍");
                break;
            case "BOS":
                ND = new BookShelfModify();
                ND.setTitle(Windows.isChanging ? "修改书架" : "新建书架");
                break;
            case "BOI":
                ND = new BookImportModify();
                ND.setTitle(Windows.isChanging ? "修改书籍入库记录" : "新建书籍入库记录");
                break;
            case "MAN":
                ND = new ManagerModify();
                ND.setTitle(Windows.isChanging ? "修改管理员" : "新建管理员");
                break;
            case "PC":
                ND = new PurchaseChecklistModify();
                ND.setTitle(Windows.isChanging ? "修改购书清单" : "新建购书清单");
                break;
            case "SUP":
                ND = new SupplierModify();
                ND.setTitle(Windows.isChanging ? "修改供应商" : "新建供应商");
                break;
            default:
                JOptionPane.showMessageDialog(null,"目标错误");
                break;
        }

        if (ND != null) {
            ND.setSize(1000, 800);
            ND.setLocation(300, 100);
            ND.setResizable(false);
            ND.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ND.setVisible(true);}
        Close.doClick();

    }




}