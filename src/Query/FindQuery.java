package Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Gui.GUI;

//1,2��
public class FindQuery {
	//public static ArrayList<ArrayList<String>> AllRowData=new ArrayList<>();

	public void query(ArrayList<String> checkedAttr, ArrayList<String> searchRange) {
		//Bdate, Address �� ���õ� attr��ȯ�մϴ� ��ü�ϰ�� ��ü ��ü ��ȯ
		/*
		System.out.println("=============checkedAttr==================");
		for(String s:checkedAttr) {
			System.out.println(s);
		}
		
		//ex)����, F
		// [0]�� ī�װ�=> ��ü, �μ�, ���� ��
		// [1]�� ����=>����μ�����? �����������?
		System.out.println("=============searchRange==================");
		for(String s:searchRange) {
			System.out.println(s);
		}
		*/
		//����κ�
		String url = "jdbc:mysql://localhost:3306/COMPANY?serverTimezone=UTC";
		String user = "root";
		String pwd = "2580";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);

			//jdbc �����κ�
			//���ڷ� ���� ���ǵ�� ������ �ۼ��ؼ� ������ �˴ϴ�.
			// ���ڿ� ���缭 SELECT�� �߰� ����, �ʿ��� ǥ�� �����ϱ� ���ؼ� boolean���� ���
			String[] Attribute = {"CONCAT(E.Fname, ' ', E.minit, ' ', E.Lname) NAME", "E.Ssn", "E.Bdate", "E.Address", "E.Sex", "E.Salary", "CONCAT(S.fname, ' ', S.minit, ' ', S.Lname) Supervisor", " Dname AS DEPARTMENT"};
			String SQL = "SELECT ";
			boolean employee = false, supervisor = false, department = false;
			int att = 0;
			while(att < checkedAttr.size()) {
				switch(checkedAttr.get(att)) {
				case "Name":
					if(att == 0)
						SQL += (Attribute[0] + " ");
					else
						SQL += ("," + Attribute[0] + " ");
					employee = true;
					break;
				case "Ssn":
					if(att == 0)
						SQL += (Attribute[1] + " ");
					else
						SQL += ("," + Attribute[1] + " ");
					employee = true;
					break;
				case "Bdate":
					if(att == 0)
						SQL += (Attribute[2] + " ");
					else
						SQL += ("," + Attribute[2] + " ");
					employee = true;
					break;
				case "Address":
					if(att == 0)
						SQL += (Attribute[3] + " ");
					else
						SQL += ("," + Attribute[3] + " ");
					employee = true;
					break;
				case "Sex":
					if(att == 0)
						SQL += (Attribute[4] + " ");
					else
						SQL += ("," + Attribute[4] + " ");
					employee = true;
					break;
				case "Salary":
					if(att == 0)
						SQL += (Attribute[5] + " ");
					else
						SQL += ("," + Attribute[5] + " ");
					employee = true;
					break;
				case "Supervisor":
					if(att == 0)
						SQL += (Attribute[6] + " ");
					else
						SQL += ("," + Attribute[6] + " ");
					supervisor = true;
					break;
				case "Department":
					if(att == 0)
						SQL += (Attribute[7] + " ");
					else
						SQL += ("," + Attribute[7] + " ");
					department = true;
					break;
				}
				att++;
			}
			
			// �ʿ��� ǥ ���� ������ FROM�� �߰�
			if((supervisor == true && department == true)) {
				String from = " FROM EMPLOYEE E LEFT JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn JOIN DEPARTMENT ON E.Dno = Dnumber ";
				SQL += from;
			}
			else if(department == true && supervisor == false) {
				String from = " FROM EMPLOYEE E JOIN DEPARTMENT ON E.Dno = Dnumber ";
				SQL += from;
			}
			else if(supervisor == true && department == false) {
				String from = " FROM EMPLOYEE E LEFT JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn ";
				SQL += from;
			}
			else if(employee == false && department == false && supervisor == false) {
				JOptionPane.showMessageDialog(null, "�ּ� 1�� �̻��� �Ӽ��� üũ���ּ���.","���",JOptionPane.WARNING_MESSAGE);
				con.close();
				return;
			}
			else {
				String from = "FROM EMPLOYEE E ";
				SQL += from;
			}
			
			//ã�� ���ǿ� ���� WHERE�� �߰�
			boolean findAll = false;
			boolean findSuper = false;
			switch(searchRange.get(0)) {
			case "��ü":
				findAll = true;
				break;
			case "�μ�":
				if(department == true)
					SQL += ("WHERE Dname = ?");
				else
					SQL += ("JOIN DEPARTMENT ON E.Dno = Dnumber WHERE Dname = ?");
				break;
			case "����":
				SQL += "WHERE E.Sex = ?";
				break;
			case "����":
				SQL += "WHERE E.Salary > ?";
				break;
			case "����":
				SQL += "WHERE MONTH(E.Bdate) = ?";
				break;
			case "��������":
				findSuper = true;
				if(supervisor)
					SQL += "WHERE CONCAT(S.FNAME, ' ', S.MINIT, ' ', S.LNAME) LIKE ?";
				else
					SQL += ("JOIN EMPLOYEE S ON E.Super_Ssn = S.Ssn WHERE CONCAT(S.FNAME, ' ', S.MINIT, ' ', S.LNAME) LIKE ?");
				break;	
			}
			
			//System.out.println(SQL);
			
			//ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE �� �־����
			PreparedStatement p = con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			// ��ü�� �ƴϸ� �׳� ����, ������ �ִٸ� �ű⿡ �°� ?�� ���ڿ� �Է�
			if (findAll == false) {
				p.clearParameters();
				if(findSuper) {
					p.setString(1, "%" + searchRange.get(1) + "%");
				}
				else {
					p.setString(1, searchRange.get(1));
				}
			}
			
			//��Ű�� ������ ����
			ResultSetMetaData rsmd = p.getMetaData();
			ResultSet r = p.executeQuery();


			
			
			//�� �Ʒ��δº��ʿ����
			
			
			r.last();
			int rowCount=r.getRow();
			r.beforeFirst();
			int columnCount = rsmd.getColumnCount();
			
			
			//�÷� ����Ʈ
			ArrayList<String> col = new ArrayList<>();
			//�ο� ����Ʈ. 
			Object[][] rows = new Object[rowCount][columnCount+1];
			

			//AllRowData = new ArrayList<>();

			//�÷�����(��Ű��)
			for (int i = 1; i <= columnCount; i++) {
				String name = rsmd.getColumnName(i);
				col.add(name);
				
				//System.out.println(name);
			}
			
			//üũ�ڽ������� 0��° �÷��� �����÷� �߰�
			col.add(0, "����");

			//�ο�����
			int Count = 0;
			while (r.next()) {
				
				//üũ�ڽ������� 0��° �÷��� false ����
				rows[Count][0]=false;
				
				//ArrayList<String> AllRowDataElement=new ArrayList<String>();
				for (int colCount = 1; colCount <= columnCount; colCount++) {
					rows[Count][colCount] = r.getString(colCount);
					//AllRowDataElement.add(r.getString(colCount));
					//System.out.println(rows[Count][colCount]);
				}
				Count++;
				//AllRowData.add(AllRowDataElement);
			}
			GUI.getGUI().tablePanel.setTable(col, rows);

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
