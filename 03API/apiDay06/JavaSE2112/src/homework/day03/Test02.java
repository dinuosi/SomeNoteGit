package homework.day03;
/**
 * 执行程序，分析并解决问题
 * 
 * NumberFormatException出现的情况通常是由包装类将字符串解析为基本类型时,由于字符串内容不能正确描述基本类型导致该异常.
 * 数字    格式      异常
 * 
 * 
 * @author Xiloer
 *
 */
public class Test02 {
	public static void main(String[] args) {
		/*
		 * 原因:字符串中含有了空格
		 */
		String num = "123 ";
		int d = Integer.parseInt(num);
		System.out.println(d);

		/*
		 * 原因:字符串若表示的是小数则不能转换为整数
		 */
//		num = "123.456";
		num = "123456";
		d = Integer.parseInt(num);
		System.out.println(num);

		/*
		 * 原因:语法错误，不能使用中文的分号
		 */
		num = "123";
		d = Integer.parseInt(num);
		System.out.println(d);
	}
}
