import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date(System.currentTimeMillis());
		DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(format1.format(date));
		String name = format1.format(date);
		System.out.println(name);

	}

}
