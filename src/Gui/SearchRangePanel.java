package Gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchRangePanel extends JPanel{
	
	String SearchOption[]= {"��ü","�μ�","����","����","����","��������"};
	JComboBox comboBox=new JComboBox<String>(SearchOption);
	
	String Department[]= {"Research","Administration","Headquarters"};
	JComboBox DepartmentComboBox=new JComboBox<String>(Department);
	

	String Sex[]= {"F","M"};
	JComboBox SexComboBox=new JComboBox<String>(Sex);
	
	JTextField SalaryJTextField=new JTextField(20);
	
	String Month[]= {"1","2","3","4","5","6","7","8","9","10","11","12"};
	JComboBox BirthdayComboBox=new JComboBox<String>(Month);
	
	//��������
	JTextField SubordinateJTextField=new JTextField(20);
	
	public SearchRangePanel() {
		this.add(new JLabel("�˻�����"));
		
		this.add(comboBox);

		
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	DepartmentComboBox.setVisible(false);
		    	SexComboBox.setVisible(false);
		    	SalaryJTextField.setVisible(false);
		    	BirthdayComboBox.setVisible(false);
		    	SubordinateJTextField.setVisible(false);
		    	
		        if(comboBox.getSelectedItem().toString().equals("��ü")) {
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("�μ�")) {
		        	DepartmentComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("����")) {
		        	SexComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("����")) {
		        	SalaryJTextField.removeAll();
		        	SalaryJTextField.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("����")) {
		        	BirthdayComboBox.setVisible(true);
		        	
		        }else if(comboBox.getSelectedItem().toString().equals("��������")) {
		        	SubordinateJTextField.removeAll();
		        	SubordinateJTextField.setVisible(true);
		        	
		        }
		        GUI.getGUI().frame.revalidate();
		    }
		});
		
		this.add(DepartmentComboBox);
		this.add(SexComboBox);
		this.add(SalaryJTextField);
		this.add(BirthdayComboBox);
		this.add(SubordinateJTextField);
    	DepartmentComboBox.setVisible(false);
    	SexComboBox.setVisible(false);
    	SalaryJTextField.setVisible(false);
    	BirthdayComboBox.setVisible(false);
    	SubordinateJTextField.setVisible(false);

		
	}
	
	//[0]�� ī�װ�=> ��ü, �μ�, ���� ��
	//[1]�� ����=>����μ�����? �����������?
	public ArrayList<String> getSearchRange() {
		ArrayList<String> result= new ArrayList<String>();
		result.add(comboBox.getSelectedItem().toString());
		if(comboBox.getSelectedItem().toString().equals("��ü")) {
			result.add("��ü");
        }else if(comboBox.getSelectedItem().toString().equals("�μ�")) {
        	result.add(DepartmentComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("����")) {
        	result.add(SexComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("����")) {
        	result.add(SalaryJTextField.getText());
        	
        }else if(comboBox.getSelectedItem().toString().equals("����")) {
        	result.add(BirthdayComboBox.getSelectedItem().toString());
        	
        }else if(comboBox.getSelectedItem().toString().equals("��������")) {
        	result.add(SubordinateJTextField.getText());
        	
        }
		
		return result;
	}

}
