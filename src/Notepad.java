import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class Notepad extends JFrame implements ActionListener{
	
	public static void main(String[] args) {
		
		new Notepad();
	}
	
	JMenuBar jmb=new JMenuBar();//�˵������
	JMenu jm1,editMenu;//�˵�
	JMenuItem jmi1,jmi2,copy,cut,paste;//�˵���
	
	//�ı���
	JTextArea jta;
	
	
	public Notepad(){		
		
		//�����˵������
		jmb=new JMenuBar();
		
		//�����˵�
		jm1=new JMenu("File");
		editMenu=new JMenu("Edit");
		
		//�����˵���
		jmi1=new JMenuItem("open");
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2=new JMenuItem("save");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		copy=new JMenuItem("copy");
		copy.addActionListener(this);
		cut=new JMenuItem("cut");
		cut.addActionListener(this);
		paste=new JMenuItem("paste");
		paste.addActionListener(this);
		
		//���˵�����ӵ��˵���
		jm1.add(jmi1);
		jm1.addSeparator();
		jm1.add(jmi2);
		jm1.addSeparator(); 
		editMenu.add(copy);
		editMenu.addSeparator();
		editMenu.add(cut);
		editMenu.addSeparator();
		editMenu.add(paste);
		editMenu.addSeparator();

		//���˵���ӵ��˵�����
		jmb.add(jm1);
		jmb.add(editMenu);
		
		//���˵�����ӵ�������
		this.setJMenuBar(jmb);
		

		//����JtextArea
		jta=new JTextArea();
		
		//���ı�����ӵ�������
		this.add(jta);
		
		//������
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
		//����Jframe������
		this.setTitle("NotePad");
		this.setSize(500, 400);
		this.setLocation(400,300);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//�ж�ActionEvent����һ�ַ���
		if(e.getSource()==copy){
			jta.copy();
		}
		if(e.getSource()==cut){
			jta.copy();
		}
		if(e.getSource()==paste){
			jta.paste();
		}
		
		 //�ж��ĸ��˵���ѡ��
		if(e.getActionCommand().equals("open")){
			//�ļ�ѡ��
			JFileChooser jfc1=new JFileChooser();
			//��������
			jfc1.setDialogTitle("��ѡ���ļ�");
			//����Ĭ��ֵ
			jfc1.showOpenDialog(null);
			//��ʾ����
			jfc1.setVisible(true);
			//�õ��û�ѡ���ȫ·��
			String filename=jfc1.getSelectedFile().getAbsolutePath();
			//��ѡ���ļ������ڴ�
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr=new FileReader(filename);
				//ת��buffer��
				br=new BufferedReader(fr);
				
				//�������ļ�����Ϣ��ʾ���ı���
				//����ÿһ�е���Ϣ
				String s="";
				//�������е�����
				String allCon="";
				while((s=br.readLine())!=null){
					allCon+=s+"\r\n";
				}
				//���õ�jta
				jta.setText(allCon);
			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}else if(e.getActionCommand().equals("save")){
			//�ļ�ѡ��
			JFileChooser jfc2=new JFileChooser();
			//��������
			jfc2.setDialogTitle("���Ϊ");
			//����Ĭ��ֵ
			jfc2.showSaveDialog(null);
			//��ʾ����
			jfc2.setVisible(true);
			//�õ�ȫ·����
			String filename=jfc2.getSelectedFile().getAbsolutePath();
			//���ı��������ݶ����ڴ�
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {
				fw=new FileWriter(filename);
				bw=new BufferedWriter(fw);
				//д���ļ�
				bw.write(this.jta.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					//�ر��ļ�
					bw.close();
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	

}
