import java.sql.*;
import java.util.Random;

public class WriteDataToDatabase {

    static Random r = new Random();

    public static void main(String[] args) throws Exception {
        // 加载 JDBC 驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 建立数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/xiaowang", "root", "root");

        // 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement("INSERT INTO resident (resident_id,name, gender, id_card,address,contact,family_members,type) VALUES (?, ?, ?, ?, ?, ?,?,?)");

        // 生成随机数据
        for (int i = 2; i < 100; i++) {
            statement.setInt(1, generateID(i));
            statement.setString(2, generateName());
            statement.setString(3, generateGender());
            statement.setString(4, generateIdcard());
            statement.setString(5, generateAddress());
            statement.setString(6, generatePhoneNumber());
            statement.setString(7, generateFamily());
            statement.setString(8, generateType());

            System.out.println(statement.toString());

            // 执行 SQL 语句
            statement.executeUpdate();
        }
        System.out.println("ok!");

        // 关闭数据库连接
        connection.close();
    }

    // 生成id
    private static int generateID(int i) {
        return i;
    }

    // 生成随机姓名
    private static String generateName() {
        String[] firstNames = {"张", "王", "李", "赵", "刘", "陈", "杨", "黄", "周", "吴"};
        String[] lastNames = {"三", "明", "浩", "宇", "飞", "云", "娜", "静", "雅", "丽"};
        return firstNames[(int) Math.floor(Math.random() * firstNames.length)] + lastNames[(int) Math.floor(Math.random() * lastNames.length)];
    }

    // 生成随机身份号码
    private static String generateIdcard() {
        // 生成随机地区码
        String[] areas = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "72", "73", "74", "75", "81", "82", "83", "85", "91", "92", "93", "94", "95"};
        String area = areas[(int) Math.floor(Math.random() * areas.length)];

        // 生成随机出生日期
        int year = (int) (Math.floor(Math.random() * 100) + 1970);
        int month = (int) (Math.floor(Math.random() * 12) + 1);
        int day = (int) (Math.floor(Math.random() * 28) + 1);
        String birthday = String.format("%04d%02d%02d", year, month, day);

        // 生成随机顺序码
        String sequence = "";
        for (int i = 0; i < 12; i++) {
            sequence += r.nextInt(9);
        }

        // 返回身份证号码
        return area + birthday + sequence;


    }

    // 生成随机性别
    private static String generateGender() {
        return Math.random() > 0.5 ? "0" : "1";
    }

    // 生成随机地址
    private static String generateAddress() {
        String[] provinces = {"北京", "上海", "天津", "重庆",
                "河北", "山西", "辽宁", "吉林",
                "黑龙江", "江苏", "浙江", "安徽",
                "福建", "江西", "山东", "河南",
                "湖北", "湖南", "广东", "海南",
                "四川", "贵州", "云南", "陕西",
                "甘肃", "青海", "宁夏", "新疆"};
        String[] cities = {"某一个市辖区", "某一个县", "某一个县级市"};
        String[] streets = {"某一个街道", "某一个路", "某一个巷", "某一个道"};
        return provinces[r.nextInt(28)] + cities[r.nextInt(3)] + streets[r.nextInt(4)];
    }

    // 生成随机电话号码
    private static String generatePhoneNumber() {
        return
                r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10) + "" +
                        r.nextInt(10);
    }

    // 生成随机家庭关系
    private static String generateFamily() {
        String[] familyProviders = {"父亲", "母亲", "子女", "兄弟", "爷孙"};
        return familyProviders[r.nextInt(5)];
    }

    // 生成随机类型
    private static String generateType() {
        String[] types = {"1", "2", "3", "4"};
        return types[r.nextInt(4)];
    }
}
