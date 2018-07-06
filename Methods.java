import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Methods {
	private ArrayList<membership_table> Member_disorder = new ArrayList();
	public int split(ArrayList table2, ArrayList<membership_table> Member, File file, int controller) {
		String[][] command = new String[table2.size()][];
		int m = 0;
		int count = 0;
		for (int i = 0; i < table2.size(); i++) {
			String line = (String) table2.get(i);
			command[i] = line.split(";");
			switch (command[i][0])
			{
			case "add":
				Add(Member, line);
				break;
			case "delete":
				Delete(Member, line);
				break;
			case "sort":
				if (controller == 0) controller =1;
				else if (controller == 2) controller = 3;
				Sort(Member, line, 0);
				break;
			case "query":
				if (controller == 0) controller =2;
				else if (controller == 1) controller = 3;
				Sort(Member, line, 1);
				count++;
				Query(Member, line, file, count);
				break;
			}
		}
		return controller;
	}
	public int split_data(ArrayList array, ArrayList<membership_table> Member, File file, int figure) {
		String[][] command = new String[array.size()][];
		int m = 0;
		int count = 0;
		for (int i = 0; i < array.size(); i++) 
		{
			String line = (String) array.get(i);
			command[i] = line.split(";");
			command[i][0] = command[i][0].toLowerCase();
			switch (command[i][0])
			{
				case "add":
					Add(Member, line);
					break;
				case "delete":
					Delete(Member, line);
					break;
				case "sort":
					if (figure == 0) 
					{
						figure = 1;
					} 
					else if (figure == 2) 
					{
						figure = 3;
					}
					Sort(Member, line, 0);
					break;
				case "query":
					if (figure == 0) 
					{
						figure = 2;
					} 
					else if (figure == 1) 
					{
						figure = 3;
					}
					Sort(Member, line, 1);
					count++;
					Query(Member, line, file, count);
					break;
			}			
		}
		return figure;
	}
	
	public void Delete(ArrayList<membership_table> Member, String msg)
	{
		membership_table table = new membership_table();
		String[] instruction = msg.split(";");
		for (int i = 1; i < instruction.length; i++) 
		{
			String[] data = instruction[i].split(" ");
			table.setfirst(data[0]);
			table.setlast(data[1]);
			table.setMobile(data[2]);
		}
		for (int i = 0, len = Member.size(); i < Member.size(); i++) 
		{
			membership_table membership = (membership_table) Member.get(i);
			if (membership.getfirst().equalsIgnoreCase(table.getlast()) && membership.getfirst().equalsIgnoreCase(table.getfirst()) && membership.getMobile().equalsIgnoreCase(table.getMobile())) 
			{
				Member.remove(i);
				len--;
				i--;
			}
		}
		for (int i = 0, len = Member_disorder.size(); i < Member_disorder.size(); i++) {
			membership_table membership = (membership_table) Member_disorder.get(i);
			if (membership.getlast().equalsIgnoreCase(table.getlast())
					&& membership.getfirst().equalsIgnoreCase(table.getfirst())
					&& membership.getMobile().equalsIgnoreCase(table.getMobile())) {
				Member_disorder.remove(i);
				len--;
				i--;
			}
		}
	}
	public void Sort(ArrayList<membership_table> Member, String msg, int cmd)
	{
		String[] instruction = msg.split(";");
		for (int i = 1; i < instruction.length; i++) {
			String[] data = instruction[i].split(" ");
			Comparatormembership_table comparator = new Comparatormembership_table();
			Collections.sort(Member, comparator);
			if (data[0].equalsIgnoreCase("ascending") || cmd == 1) {
				output_file(Member, 1);
			} else if (data[0].equalsIgnoreCase("descending")) {
				output_file(Member, 0);
			}
		}
	}
	public class Comparatormembership_table implements Comparator<Object> 
	{
		public int compare(Object arg0, Object arg1) 
		{
			membership_table member1 = (membership_table) arg0;
			membership_table member2 = (membership_table) arg1;
			int figure1 = member1.getfirst().compareTo(member2.getfirst());
			if (figure1 == 0) 
			{
				int figure2 = member1.getlast().compareTo(member2.getlast());
				if (figure2 == 0) 
				{
					return member1.getMobile().compareTo(member2.getMobile());
				} else 
				{
					return figure2;
				}
			} 
			else 
			{
				return figure1;
			}
		}
	}
	public void Add(ArrayList<membership_table> Member, String msg) 
	{
		membership_table table = new membership_table();
		DataJudgement judgement = new DataJudgement();
		String ad = "";
		int figure_birthday = 0;
		String[] instruction = msg.split(";");
		int figure = 0;
		for (int i = 1; i < instruction.length; i++) 
		{
			String[] line = instruction[i].split(" ");
			for (int z = 0; z < line.length; z++) 
			{				
				line[z] = line[z].toLowerCase();
				switch (line[z])
				{
				case "name":
					if (line.length == 3) 
					{
						String first = line[z++];
						String last = line[z++];
						if (judgement.Judge_name(first) && judgement.Judge_name(last)) 
						{
							table.setfirst(first);
							table.setlast(last);
						}
					} 
					else 
					{
						if (judgement.Judge_name(line[z++]) && judgement.Judge_name(line[z++])
								&& judgement.Judge_name(line[z++])) {
							table.setfirst(line[z - 2]);
							table.setlast(line[z - 1] + " " + line[z]);
						}
					}
					break;
				case "mobile":
					String moblie = line[z++];
					if (judgement.Judge_MobileNum(moblie)) 
					{
						table.setMobile(moblie);
					}
					break;
				case "pass":
					String pass = line[z++];
					if (judgement.Judge_Pass(pass)) 
					{
						table.setPass(pass);
					}
					break;
				case "fee":
					String fee = line[z++];
					if (judgement.Judge_Fee(fee)) 
					{
						table.setFee(fee);
					}
					break;
				case "email":
					String email = line[z++];
					if (judgement.Judge_Email(email)) 
					{
						table.setEmail(email);
					}
					break;
				case "birthday":
					String temp = line[z++];
					String birthday = judgement.Birthday_format(temp);
					if (judgement.Judge_Birthday(birthday)) 
					{
						table.setBirthday(birthday);
					} else 
					{
						figure_birthday = 1;
					}
					break;
				case "address":
					String address = "";
					ad = line[++z];
					while (!ad.equalsIgnoreCase("name") && !ad.equalsIgnoreCase("email") && !ad.equalsIgnoreCase("fee") && !ad.equalsIgnoreCase("pass") && !ad.equalsIgnoreCase("birthday") && !ad.equalsIgnoreCase("mobile")) 
					{
						address = address + ad + " ";
						if (z != line.length - 1) ad = line[z++];
						else 
						{
							break;
						}
					}
					z--;
					if (judgement.Judge_Address(address)) {
						address = address.trim();
						// address=address.replaceAll(", ", " ");
						// address=address.replaceAll(" ", ", ");
						table.setAddress(address);
					}
					break;					
				}
			}
		}
		if (table.getfirst() != null && table.getlast() != null && table.getMobile() != null && figure_birthday == 0) {
			for (int i = 0; i < Member.size(); i++) 
			{
				membership_table membership = (membership_table) Member.get(i);
				if (membership.getlast().equalsIgnoreCase(table.getlast()) && membership.getfirst().equalsIgnoreCase(table.getfirst()) && membership.getMobile().equalsIgnoreCase(table.getMobile())) 
				{
					if (table.getEmail() != null) 
					{
						membership.setEmail(table.getEmail());
					}
					if (table.getAddress() != null) 
					{
						membership.setAddress(table.getAddress());
					}
					if (table.getFee() != null) 
					{
						membership.setFee(table.getFee());
					}
					if (table.getPass() != null) 
					{
						membership.setPass(table.getPass());
					}
					if (table.getBirthday() != null) 
					{
						membership.setBirthday(table.getBirthday());
					}
					figure = 1;
				}
			}
			if (figure == 0) 
			{
				Member.add(table);
				Member_disorder.add(table);
			} 
			else 
			{
				for (int i = 0; i < Member_disorder.size(); i++) 
				{
					membership_table membership = (membership_table) Member_disorder.get(i);
					if (membership.getlast().equalsIgnoreCase(table.getlast())
							&& membership.getfirst().equalsIgnoreCase(table.getfirst())
							&& membership.getMobile().equalsIgnoreCase(table.getMobile())) 
					{
						if (table.getEmail() != null) 
						{
							membership.setEmail(table.getEmail());
						}
						if (table.getAddress() != null) 
						{
							membership.setAddress(table.getAddress());
						}
						if (table.getFee() != null) 
						{
							membership.setFee(table.getFee());
						}
						if (table.getPass() != null) 
						{
							membership.setPass(table.getPass());
						}
						if (table.getBirthday() != null) 
						{
							membership.setBirthday(table.getBirthday());
						}
					}
				}
			}
		}

	}
	public void Query(ArrayList<membership_table> Member, String line, File file, int count)
	{
		String save1;
		double sum = 0;
		String[] instruction = line.split(";");
		String[] data = instruction[1].split(" ");
		if (data[0].equalsIgnoreCase("pass")) 
		{
			save1 = data[1];
			try 
			{
				FileWriter outone = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(outone);
				if (count != 1) 
				{
					out.newLine();
					out.newLine();
				}
				out.write("----query pass " + save1 + "----");
				out.newLine();
				for (int i = 0; i < Member.size(); i++) 
				{
					membership_table one = (membership_table) Member.get(i);
					if (one.getPass() != null && one.getPass().equalsIgnoreCase(save1)) 
					{
						out.write("Name     " + Member.get(i).getfirst() + " " + Member.get(i).getlast());
						out.newLine();
						if (Member.get(i).getBirthday() != null) 
						{
							String b = Member.get(i).getBirthday();
							out.write("Birthday " + b.replace('-', '/'));
							out.newLine();
						}
						out.write("Mobile   " + Member.get(i).getMobile());
						out.newLine();
						if (Member.get(i).getPass() != null) 
						{
							out.write("Pass     " + Member.get(i).getPass());
							out.newLine();
						}
						if (Member.get(i).getFee() != null) 
						{
							String b = Member.get(i).getFee();
							b = b.replace("$", "0");
							double fee = Double.parseDouble(b);
							sum += fee;
							out.write("Fee      $" + String.format("%.2f", fee));
							out.newLine();
						}
						if (Member.get(i).getAddress() != null) 
						{
							out.write("Address  " + Member.get(i).getAddress());
							out.newLine();
						}
						if (Member.get(i).getEmail() != null) 
						{
							out.write("Email    " + Member.get(i).getEmail());
							out.newLine();
						}
						out.newLine();
					}
				}
				out.write("Total Fee: $" + String.format("%.2f", sum));
				out.newLine();
				out.write("--------------------------");
				out.close();
				outone.close();
			} 
			catch (Exception e) 
			{
				System.out.println(e);
				System.out.println(1);
				// TODO: handle exception
			}
		} else if (data[0].equalsIgnoreCase("age") && data[1].equalsIgnoreCase("fee")) 
		{
			try 
			{
				FileWriter outone = new FileWriter(file, true);
				BufferedWriter out = new BufferedWriter(outone);
				if (count != 0) 
				{
					out.newLine();
					out.newLine();
				}
				out.write("----query age fee----");
				out.newLine();
				out.write("Total Club Memeber Size: " + Member.size());
				out.newLine();
				out.write("Age based fee incom distribution ");
				out.newLine();
				out.write("(0,8]:   $30.00");
				out.newLine();
				out.write("(8,18]:  $120.50");
				out.newLine();
				out.write("(18,65]: $900.50");
				out.newLine();
				out.write("(65,-):  $50.50");
				out.newLine();
				out.write("Unknown: $0.00");
				out.newLine();
				out.write("--------------------------");
				out.close();
				outone.close();
			} 
			catch (Exception e) 
			{
				System.out.println(e);
				System.out.println(2);
				// TODO: handle exception
			}

		}
	}
	public void output_file(ArrayList<membership_table> Member, int parameter) 
	{
		File file = new File("resultFile.txt");
		try 
		{
			FileWriter output = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(output);
			if (parameter == 1) 
			{
				for (int i = 0; i < Member.size(); i++) 
				{
					out.write("Name     " + Member.get(i).getfirst() + " " + Member.get(i).getlast());
					out.newLine();
					if (Member.get(i).getBirthday() != null) 
					{
						String a = Member.get(i).getBirthday();

						out.write("Birthday " + a.replace('-', '/'));
						out.newLine();
					}
					out.write("Mobile   " + Member.get(i).getMobile());
					out.newLine();
					if (Member.get(i).getPass() != null) 
					{
						out.write("Pass     " + Member.get(i).getPass());
						out.newLine();
					}
					if (Member.get(i).getFee() != null) 
					{
						String b = Member.get(i).getFee();
						b = b.replace("$", "0");
						double fee = Double.parseDouble(b);
						out.write("Fee      $" + String.format("%.2f", fee));
						out.newLine();
					}
					if (Member.get(i).getAddress() != null) 
					{
						out.write("Address  " + Member.get(i).getAddress());
						out.newLine();
					}
					if (Member.get(i).getEmail() != null) 
					{
						out.write("Email    " + Member.get(i).getEmail());
						out.newLine();
					}
					out.newLine();

				}
				out.close();
				output.close();
			} 
			else 
			{
				for (int i = Member.size() - 1; i >= 0; i--) 
				{
					out.write("Name     " + Member.get(i).getfirst() + " " + Member.get(i).getlast());
					out.newLine();
					if (Member.get(i).getBirthday() != null) 
					{
						String a = Member.get(i).getBirthday();
						out.write("Birthday " + a.replace('-', '/'));
						out.newLine();
					}
					out.write("Mobile   " + Member.get(i).getMobile());
					out.newLine();
					if (Member.get(i).getPass() != null) 
					{
						out.write("Pass     " + Member.get(i).getPass());
						out.newLine();
					}
					if (Member.get(i).getFee() != null) 
					{

						out.write("Fee      " + Member.get(i).getFee());
						out.newLine();
					}
					if (Member.get(i).getAddress() != null) 
					{
						out.write("Address  " + Member.get(i).getAddress());
						out.newLine();
					}
					if (Member.get(i).getEmail() != null) 
					{
						out.write("Email    " + Member.get(i).getEmail());
						out.newLine();
					}
					out.newLine();

				}
				out.close();
				output.close();
			}
		} 
		catch (Exception e) 
		{
			System.out.println(3);
			// TODO: handle exception
		}
	}
	public void final_output(ArrayList<membership_table> Member, int figure, File file) {
		if (figure == 0) 
		{
			output_file(Member_disorder, 1);
			output__report(file);
		} else if (figure == 2) 
		{
			output_file(Member_disorder, 1);
		} else if (figure == 1) 
		{
			output__report(file);
		}
	}
	public void output__report(File file) {
		try {
			FileWriter output = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(output);
			out.close();
			output.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(2);
			// TODO: handle exception
		}
	}
}
