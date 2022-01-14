package org.example.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

/**
 * @Describe : 生成器——帐号、姓名、手机号、邮箱、密码都可以生成
 * @Author : SHK
 * @Date : 2022/1/12 17:14
 */
public class Generator {

    //小写
    private final static String[] LOWERCASE = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"
    };
    //大写
    private final static String[] UPPERCASE = {
            "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };
    //数字
    private final static String[] NUMBER = {
            "0","1","2","3","4","5","6","7","8","9"
    };
    //手机号码头
    private final static String[] MOBILE_FIRST = {
            "130","131","132","133","134","135","136","137","138","139","145","146","147","148","149",
            "150","151","152","155","156","157","158","159","162","165","166","167","170","171","172",
            "173","174","175","176","177","178","180","181","182","183","184","185","186","187","188",
            "189","190","191","192","193","195","196","197","198","199"
    };
    //常见邮箱后缀
    private final static String[] EMAIL_LAST = {
            "@gmail.com","@yahoo.com","@msn.com","@hotmail.com","@aol.com","@ask.com","@live.com","@qq.com",
            "@163.com","@163.net","@126.com","@sogou.com","@yahoo.com.cn","@56.com","@sohu.com","@citiz.com",
            "@aliyun.com.cn"
    };

    //符号
    private final static String[] SYMBOL = {
        "~","!","@","#","$","%","%","^","&","*","-","+","="
    };
    //百家姓
    private final static String[] SURNAME = {
            "赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
            "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
            "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
            "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
            "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
            "宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
            "羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
            "谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
            "詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
            "蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
            "璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
            "宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
            "阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
            "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
            "督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
            "哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
            "疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
            "木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
            "闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
            "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
            "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
            "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
            "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"
    };






    public static void main(String[] arge) {
        String name = createChineseName();
        System.out.println(name);

//        for(int i =0;i<10;i++) {
//            System.out.println(createMobile());
//        }

//        for(int j=0;j<12;j++) {
//            System.out.println(createEmail());
//        }

//        for(int j=0;j<5;j++) {
//            System.out.println(createAccount("G"));
//        }

        for(int j=0;j<5;j++) {
            System.out.println(createPassword(3));
        }

    }


    //获取随机中文
    public static String getChinese() {
        String str = null;
        Integer highPos, lowPos;
        Random random = new Random();
        highPos = (176 + Math.abs(random.nextInt(71)));//区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        random=new Random();
        lowPos = 161 + Math.abs(random.nextInt(94));//位码，0xA0打头，范围第1~94列

        byte[] bArr = new byte[2];
        bArr[0] = highPos.byteValue();
        bArr[1] = lowPos.byteValue();
        try {
            str = new String(bArr, "GB2312");	//区位码组合成汉字
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @Describe: 创建随机姓名
     * @Date:
     */
    public static String createChineseName() {
        int index = new Random().nextInt(SURNAME.length-1);

        String surname = SURNAME[index];

        if(new Random().nextBoolean()) {
            return surname + getChinese() + getChinese();
        } else {
            return surname + getChinese();
        }

    }

    /**
     * @Describe: 创建手机号
     * @Date:
     */
    public static String createMobile() {
        int index = new Random().nextInt(MOBILE_FIRST.length - 1 );
        String first = MOBILE_FIRST[index];
        StringBuffer buf = new StringBuffer();
        buf.append(first);
        for(int i=0;i<8;i++) {
            int n = new Random().nextInt(NUMBER.length - 1);
            buf.append(NUMBER[n]);
        }
        return buf.toString();

    }

    /**
     * @Describe: 创建邮箱
     * @Date:
     */
    public static String  createEmail() {
        int index = new Random().nextInt(EMAIL_LAST.length - 1 );
        String last = EMAIL_LAST[index];

        int len = new Random().nextInt(12) + 12 ;
        StringBuffer buf = new StringBuffer();
        for(int i=0; i < len; i++) {
            int n = new Random().nextInt(LOWERCASE.length - 1);
            buf.append(LOWERCASE[n]);
        }
        buf.append(last);

        return buf.toString();
    }

    /**
     * @Describe: 创建帐号
     * @Date:
     */
    public static String createAccount(String first) {

        String uuid = UUID.randomUUID().toString();
        //32位uuid
        uuid = uuid.replace("-", "");

        return first + uuid.substring(16, 23);
    }

    /**
     * @Describe: 创建密码
     * 复杂度  1、纯数字；2、数字+字母；3、数字+字母+符号
     * @Date:
     */
    public static String createPassword(int complexity) {

        String pwd = "Qwer!234as";

        //统一给10位密码
        if(complexity == 1) {
            StringBuffer buf = new StringBuffer();
            for(int i=0;i<10;i++) {
                int n = new Random().nextInt(NUMBER.length - 1);
                buf.append(NUMBER[n]);
            }
            pwd = buf.toString();
        }

        if(complexity == 2) {
            String uuid = UUID.randomUUID().toString();
            //32位uuid
            uuid = uuid.replace("-", "");
            pwd = uuid.substring(0, 10);
        }

        if(complexity == 3) {
            StringBuffer buf = new StringBuffer();
            //1位用小写字母
            buf.append(LOWERCASE[new Random().nextInt(LOWERCASE.length-1)]);
            //2位用大写字母
            buf.append(UPPERCASE[new Random().nextInt(UPPERCASE.length-1)]);
            //3位用数字
            buf.append(NUMBER[new Random().nextInt(NUMBER.length-1)]);
            //4位用小写字母
            buf.append(LOWERCASE[new Random().nextInt(LOWERCASE.length-1)]);
            //5位用大写字母
            buf.append(UPPERCASE[new Random().nextInt(UPPERCASE.length-1)]);
            //6位用数字
            buf.append(NUMBER[new Random().nextInt(NUMBER.length-1)]);
            //7位用符号
            buf.append(SYMBOL[new Random().nextInt(SYMBOL.length-1)]);
            //8位用数字
            buf.append(NUMBER[new Random().nextInt(NUMBER.length-1)]);
            //9位用小写字母
            buf.append(LOWERCASE[new Random().nextInt(LOWERCASE.length-1)]);
            //9位用小写字母
            buf.append(LOWERCASE[new Random().nextInt(LOWERCASE.length-1)]);
            pwd = buf.toString();
        }

        return pwd;
    }

}
