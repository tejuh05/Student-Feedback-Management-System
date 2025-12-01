package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import dao.FeedbackDAO;
import model.Feedback;
import java.util.List;

public class FeedbackUI extends JFrame {
    JTextField txtStudent, txtCourse, txtTeacher, txtRating, txtComments, txtId;
    JTable table; 
    DefaultTableModel model;

    public FeedbackUI(){
        setTitle("The Growth Tracker Of Class");
        setSize(800,500); 
        setLayout(null);

        JLabel lblId=new JLabel("ID"); 
        lblId.setBounds(20,20,100,25); 
        add(lblId);

        txtId=new JTextField(); 
        txtId.setBounds(120,20,150,25); 
        add(txtId);

        JLabel lblStudent=new JLabel("Student Name"); 
        lblStudent.setBounds(20,60,100,25); 
        add(lblStudent);

        txtStudent=new JTextField(); 
        txtStudent.setBounds(120,60,150,25); 
        add(txtStudent);

        JLabel lblCourse=new JLabel("Course Name"); 
        lblCourse.setBounds(20,100,100,25); 
        add(lblCourse);

        txtCourse=new JTextField(); 
        txtCourse.setBounds(120,100,150,25); 
        add(txtCourse);

        JLabel lblTeacher=new JLabel("Teacher Name"); 
        lblTeacher.setBounds(20,140,100,25); 
        add(lblTeacher);

        txtTeacher=new JTextField(); 
        txtTeacher.setBounds(120,140,150,25); 
        add(txtTeacher);

        JLabel lblRating=new JLabel("Rating"); 
        lblRating.setBounds(20,180,100,25); 
        add(lblRating);

        txtRating=new JTextField(); 
        txtRating.setBounds(120,180,150,25); 
        add(txtRating);

        JLabel lblComments=new JLabel("Comments"); 
        lblComments.setBounds(20,220,100,25); 
        add(lblComments);

        txtComments=new JTextField(); 
        txtComments.setBounds(120,220,150,25); 
        add(txtComments);

        JButton btnAdd=new JButton("Add"); 
        btnAdd.setBounds(20,260,100,30); 
        add(btnAdd);

        JButton btnUpdate=new JButton("Update"); 
        btnUpdate.setBounds(130,260,100,30); 
        add(btnUpdate);

        JButton btnDelete=new JButton("Delete"); 
        btnDelete.setBounds(240,260,100,30); 
        add(btnDelete);

        JButton btnView=new JButton("View All"); 
        btnView.setBounds(350,260,100,30); 
        add(btnView);

        table=new JTable(); 
        model=new DefaultTableModel(); 
        model.setColumnIdentifiers(new String[]{"ID","Student","Course","Teacher","Rating","Comments"});
        table.setModel(model);

        // Set custom cell renderer for coloring
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                c.setForeground(Color.BLACK); // default text color

                // Rating column index = 4
                if(column == 4 && value != null){
                    try {
                        int rating = Integer.parseInt(value.toString());
                        if(rating > 3){
                            c.setForeground(new Color(0,128,0)); // dark green
                        } else {
                            c.setForeground(Color.RED);
                        }
                    } catch(NumberFormatException e){
                        c.setForeground(Color.BLACK);
                    }
                }

                // Comments column index = 5
                if(column == 5 && value != null){
                    String comments = value.toString().toLowerCase();
                    if(comments.contains("good") || comments.contains("nice")){
                        c.setForeground(new Color(0,128,0)); // dark green
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                }

                return c;
            }
        });

        JScrollPane pane=new JScrollPane(table); 
        pane.setBounds(20,310,740,140); 
        add(pane);

        btnAdd.addActionListener(e->{
            Feedback f=new Feedback(); 
            f.setStudentName(txtStudent.getText()); 
            f.setCourseName(txtCourse.getText()); 
            f.setTeacherName(txtTeacher.getText()); 
            f.setRating(Integer.parseInt(txtRating.getText())); 
            f.setComments(txtComments.getText()); 
            FeedbackDAO.addFeedback(f); 
            JOptionPane.showMessageDialog(this,"Feedback Added!"); 
            clearFields(); 
        });

        btnView.addActionListener(e->loadTable());

        btnUpdate.addActionListener(e->{
            Feedback f=new Feedback(); 
            f.setId(Integer.parseInt(txtId.getText())); 
            f.setStudentName(txtStudent.getText()); 
            f.setCourseName(txtCourse.getText()); 
            f.setTeacherName(txtTeacher.getText()); 
            f.setRating(Integer.parseInt(txtRating.getText())); 
            f.setComments(txtComments.getText()); 
            FeedbackDAO.updateFeedback(f); 
            JOptionPane.showMessageDialog(this,"Feedback Updated!"); 
            clearFields(); 
        });

        btnDelete.addActionListener(e->{
            int id=Integer.parseInt(txtId.getText()); 
            FeedbackDAO.deleteFeedback(id); 
            JOptionPane.showMessageDialog(this,"Feedback Deleted!"); 
            clearFields(); 
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setVisible(true);
    }

    private void loadTable(){ 
        List<Feedback> list=FeedbackDAO.getAllFeedback(); 
        model.setRowCount(0); 
        for(Feedback f:list){ 
            model.addRow(new Object[]{f.getId(),f.getStudentName(),f.getCourseName(),f.getTeacherName(),f.getRating(),f.getComments()}); 
        } 
    }

    private void clearFields(){ 
        txtId.setText(""); 
        txtStudent.setText(""); 
        txtCourse.setText(""); 
        txtTeacher.setText(""); 
        txtRating.setText(""); 
        txtComments.setText(""); 
    }

    public static void main(String[] args){ 
        new FeedbackUI(); 
    }
}