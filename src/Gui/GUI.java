package Gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Query.AddQuery;
import Query.DeleteQuery;
import Query.FindQuery;
import Query.UpdateQuery;

public class GUI {
	
	public static GUI GUI_INSTANCE=null;
	
	public static GUI makeGUI() {
		if (GUI_INSTANCE==null) {
			GUI_INSTANCE=new GUI();
		}
		return GUI_INSTANCE;
	}
	
	public static GUI getGUI() {
		if (GUI_INSTANCE==null) {
			GUI_INSTANCE=new GUI();
		}
		return GUI_INSTANCE;
	}
	
	public AddQuery addQ=new AddQuery();
	public DeleteQuery deleteQ=new DeleteQuery();
	public FindQuery findQ=new FindQuery();
	public UpdateQuery updateQ=new UpdateQuery();
	
	
	public MainFrame frame=new MainFrame("Information Retrival System",1500,1000);
	
	public TablePanel tablePanel=new TablePanel();
	
	public UpdatePanel updatePanel=new UpdatePanel();
	
	public DeletePanel deletePanel=new DeletePanel();
	
	public AddPanel addPanel=new AddPanel();
	
	public SearchAttrCheckBoxPanel searchAttrCheckBoxPanel=new SearchAttrCheckBoxPanel();

	public GUI() {
		
		frame.setLayout(new BorderLayout());
		
		
		frame.add(searchAttrCheckBoxPanel, BorderLayout.NORTH);
		//�˻����� -�μ�-����-����:  �Է��Ѽ��ں��ٿ����̳��������˻�   -����:  1��~12�������ϸ��ش���̻����������˻�    -��������:  �Է��������Ǻ��������˻�
		//�˻����� ����, �Է°��� or ������ ����
		
		//�˻��׸� -attr
		
			//�˻��׸� �˻� ��ư
		
		//db���̺�
		tablePanel.setBounds(100, 100, 300, 300);
		
		frame.add(tablePanel, BorderLayout.CENTER);
			//���, ���� üũ�ڽ�
		
		//���� ������ ����-�ʼ��ƴ�
		//���� ���õ� �ο���-�ʼ��ƴ�
		

		//���ο� ���� �߰�
		frame.add(addPanel, BorderLayout.EAST);
		
		
		JPanel southPanel=new JPanel();
		
		southPanel.setLayout(new GridLayout(1,3));
		//����
			//���� �׸�
		
		southPanel.add(updatePanel);
		
		//���� ���� �� ����
		southPanel.add(deletePanel);
		
		
		frame.add(southPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	void refresh() {
		
	}
	
}
