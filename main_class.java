import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Scanner;
public class main_class {
	private ArrayList<membership_table> Member_disorder = new ArrayList();
    public void main (String args[])
    {
    	Methods md = new Methods();
    	Scanner scan = new Scanner(System.in);
    	ArrayList member = new ArrayList();
		ArrayList save_data = new ArrayList();
		int controller = 0;
		File file = new File("memberFile.txt");
       	File instruction = new File("InstructionFile.txt");
    	File result = new File("resultFile.txt");
    	File report = new File("reportFile.txt");
    	ReadMembersFile(file, member);
    	ReadInstructionFile(instruction, save_data);
    	controller=md.split(save_data, member, file,controller);
		md.final_output(member, controller, file);
    	
    	
    	
    	
    }
    public void ReadMembersFile(File f, ArrayList<membership_table> Member) 
    {
    	Methods md = new Methods();
		String a = "";
		Scanner sc = null;
		int controller = 0;
		membership_table table1 = new membership_table();
		ArrayList temp = new ArrayList();
		try {
			sc = new Scanner(f);
			DataJudgement data = new DataJudgement();
			while (sc.hasNext()) {
				a = sc.nextLine();
				if (a.equalsIgnoreCase("")) 
				{
					controller = 0;
					table1 = new membership_table();
					for (int i = 0; i < temp.size(); i++) 
					{
						String[] msg = ((String) temp.get(i)).split(" ");
						switch (msg[0].toLowerCase())
						{
						case "name":
							if (msg.length == 3) 
							{
								if (data.Judge_name(msg[1]) && data.Judge_name(msg[2])) 
								{
									table1.setfirst(msg[1]);
									table1.setlast(msg[2]);

								}
							} 
							else 
							{

								if (data.Judge_name(msg[1]) && data.Judge_name(msg[2]) && data.Judge_name(msg[3])) 
								{
									table1.setfirst(msg[1]);
									table1.setlast(msg[2] + " " + msg[3]);
								}
							}
							break;
						case "mobile":
							if (data.Judge_MobileNum(msg[1])) 
							{
								table1.setMobile(msg[1]);
							}
							break;
						case "pass":
							if (data.Judge_Pass(msg[1])) 
							{
								table1.setPass(msg[1]);
							}
							break;
						case "fee":
							if (data.Judge_Fee(msg[1])) 
							{
								table1.setFee(msg[1]);
							}
							break;
						case "email":
							if (data.Judge_Email(msg[1])) 
							{
								table1.setEmail(msg[1]);
							}
							break;
						case "birthday":
							String birthday = data.Birthday_format(msg[1]);
							if (data.Judge_Birthday(birthday)) 
							{
								table1.setBirthday(birthday);
							} 
							else 
							{
								controller = 1;
							}
							break;
						case "address":
							String address = "";
							for (int x = 1; x < msg.length; x++) 
							{
								address = address + msg[x] + " ";
							}
							table1.setAddress(address);
							break;
						default:
							String address1 = table1.getAddress();

							for (int x = 0; x < msg.length; x++) {
								String aString = msg[x].replaceAll("\t", "");
								address1 = address1 + aString + " ";
							}
							address1 = address1.trim();
							table1.setAddress(address1);
						};
						
					}
					if (table1.getfirst() != null && table1.getlast() != null && table1.getMobile() != null && controller == 0)
					{
						if (table1.getAddress() != null) 
						{
							if (!data.Judge_Address(table1.getAddress())) table1.setAddress(null);
						}
						Member.add(table1);
						Member_disorder.add(table1);
					}
					temp = new ArrayList();
				} else
					temp.add(a);
			}
			if (table1.getfirst() != null && table1.getlast() != null && table1.getMobile() != null) {
				table1 = new membership_table();
				controller = 0;
				for (int i = 0; i < temp.size(); i++) 
				{
					String[] msg = ((String) temp.get(i)).split(" ");
					msg[0] = msg[0].toLowerCase();
					switch (msg[0])
					{
					case "name":
						if (msg.length == 3) 
						{
							if (data.Judge_name(msg[1]) && data.Judge_name(msg[2])) 
							{
								table1.setfirst(msg[1]);
								table1.setlast(msg[2]);
							}
						} 
						else 
						{

							if (data.Judge_name(msg[1]) && data.Judge_name(msg[2])
									&& data.Judge_name(msg[3])) 
							{
								table1.setfirst(msg[1]);
								table1.setlast(msg[2] + " " + msg[3]);
							}
						}
						break;
					case "mobile":
						if (data.Judge_MobileNum(msg[1])) 
						{
							table1.setMobile(msg[1]);
						}
						break;
					case "pass":
						if (data.Judge_Pass(msg[1])) 
						{
							table1.setPass(msg[1]);
						}
						break;
					case "fee":
						if (data.Judge_Fee(msg[1])) 
						{
							table1.setFee(msg[1]);
						}
						break;
					case "email":
						if (data.Judge_Email(msg[1])) 
						{
							table1.setEmail(msg[1]);
						}
						break;
					case "birthday":
						String birthday = data.Birthday_format(msg[1]);
						if (data.Judge_Birthday(birthday)) 
						{
							table1.setBirthday(birthday);
						} 
						else 
						{
							controller = 1;
						}
						break;
					case "address":
						String address = "";
						for (int x = 1; x < msg.length; x++) {
							address = address + msg[x] + " ";
						}

						table1.setAddress(address);
						break;
					default:
						String address1 = table1.getAddress();

						for (int x = 0; x < msg.length; x++) 
						{
							String aString = msg[x].replaceAll("\t", "");
							address1 = address1 + aString + " ";
						}
						address1 = address1.trim();
						table1.setAddress(address1);
					}
				}
				if (table1.getfirst() != null && table1.getlast() != null && table1.getMobile() != null && controller == 0) {
					if (table1.getAddress() != null) 
					{
						if (!data.Judge_Address(table1.getAddress()))
							table1.setAddress(null);
					}
					Member.add(table1);
					Member_disorder.add(table1);
				}
			}
		} catch (IOException exp) 
		{
			System.out.println("File read error:" + exp);
		}
	}

	/**
	 * read instruction file and save all the content on an array list named
	 * "save_data" split by ";" .
	 */
	public void ReadInstructionFile(File f, ArrayList<String> table1) {
		String b = "";
		Scanner sc = null;
		try {
			sc = new Scanner(f);
			DataJudgement data = new DataJudgement();
			while (sc.hasNext()) {
				String a = sc.next();
				if (!a.equalsIgnoreCase("add") && !a.equalsIgnoreCase("delete") && !a.equalsIgnoreCase("Sort")
						&& !a.equalsIgnoreCase("Query")) {
					b = b + a + sc.nextLine() + ' ';

				} else {
					b = b.replaceAll("; ", ";");
					table1.add(b);
					b = "";
					b = a + ";" + sc.nextLine() + ' ';
				}
			}
			b = b.replaceAll("; ", ";");
			table1.add(b);
			table1.remove(0);
		} catch (IOException exp) {
			System.out.println("e");
		}
	}

	/**
	 * read "save_data" and dispose all instruction:
	 * "add","delete","sort","query"
	 */
	public int split(ArrayList two, ArrayList<membership_table> Member, File file, int controller) {
		String[][] command = new String[two.size()][];
		Methods md = new Methods();
		int m = 0;
		int count = 0;
		for (int i = 0; i < two.size(); i++) 
		{
			String line = (String) two.get(i);
			command[i] = line.split(";");
			command[i][0] = command[i][0].toLowerCase();
			switch (command[i][0])
			{
				case "add":
					md.Add(Member, line);
					break;
				case "delete":
					md.Delete(Member, line);
					break;
				case "sort":
					if (controller == 0) 
					{
					controller = 1;
					} 
					else if (controller == 2) 
					{
					controller = 3;
					}
					md.Sort(Member, line, 0);
					break;
				case "query":
					if (controller == 0) 
					{
					controller = 2;
					} 
					else if (controller == 1) 
					{
					controller = 3;
					}
					md.Sort(Member, line, 1);
					count++;
					md.Query(Member, line, file, count);
					break;
			}
		}
		return controller;

	}
}
