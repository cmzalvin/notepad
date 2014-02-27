import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class Notepad extends JFrame implements ActionListener{
	
	public static void main(String[] args) {
		
		new Notepad();
	}
	
	JMenuBar jmb=new JMenuBar();//菜单条组件
	JMenu jm1,editMenu;//菜单
	JMenuItem jmi1,jmi2,copy,cut,paste;//菜单项
	
	//文本域
	JTextArea jta;
	
	
	public Notepad(){		
		
		//创建菜单条组件
		jmb=new JMenuBar();
		
		//创建菜单
		jm1=new JMenu("File");
		editMenu=new JMenu("Edit");
		
		//创建菜单项
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
		
		//将菜单项添加到菜单上
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

		//讲菜单添加到菜单条上
		jmb.add(jm1);
		jmb.add(editMenu);
		
		//将菜单条添加到窗体上
		this.setJMenuBar(jmb);
		

		//创建JtextArea
		jta=new JTextArea();
		
		//将文本域添加到窗体上
		this.add(jta);
		
		//滚动条
		JScrollPane jsp=new JScrollPane(jta);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
		//设置Jframe的属性
		this.setTitle("NotePad");
		this.setSize(500, 400);
		this.setLocation(400,300);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//判断ActionEvent的另一种方法
		if(e.getSource()==copy){
			jta.copy();
		}
		if(e.getSource()==cut){
			jta.copy();
		}
		if(e.getSource()==paste){
			jta.paste();
		}
		
		 //判断哪个菜单被选中
		if(e.getActionCommand().equals("open")){
			//文件选中
			JFileChooser jfc1=new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("请选择文件");
			//保持默认值
			jfc1.showOpenDialog(null);
			//显示框体
			jfc1.setVisible(true);
			//得到用户选择的全路径
			String filename=jfc1.getSelectedFile().getAbsolutePath();
			//所选的文件读入内存
			FileReader fr=null;
			BufferedReader br=null;
			try {
				fr=new FileReader(filename);
				//转入buffer流
				br=new BufferedReader(fr);
				
				//读到的文件的信息显示到文本域
				//接受每一行的信息
				String s="";
				//保存所有的内容
				String allCon="";
				while((s=br.readLine())!=null){
					allCon+=s+"\r\n";
				}
				//放置到jta
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
			//文件选中
			JFileChooser jfc2=new JFileChooser();
			//设置名字
			jfc2.setDialogTitle("另存为");
			//保持默认值
			jfc2.showSaveDialog(null);
			//显示框体
			jfc2.setVisible(true);
			//得到全路径名
			String filename=jfc2.getSelectedFile().getAbsolutePath();
			//把文本区的内容读入内存
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {
				fw=new FileWriter(filename);
				bw=new BufferedWriter(fw);
				//写进文件
				bw.write(this.jta.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					//关闭文件
					bw.close();
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	

}
