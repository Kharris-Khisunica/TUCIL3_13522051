import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.util.*;

public class WordLadderGUI {
    private JFrame frame;
    private JComboBox<String> algorithmComboBox;
    private JTextField startNodeField, goalNodeField;
    private JButton searchButton;
    private JTextArea infoArea;
    private JPanel resultPanel;

    public WordLadderGUI() {
        frame = new JFrame("Word Ladder Solver");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setupInputAndButton();
        setupResultPanel();
        setupInfoArea();

        frame.setVisible(true);
    }

    private void setupInputAndButton() {
        // Setup Input Panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Setup Fields Panel
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 2));
        fieldsPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        algorithmComboBox = new JComboBox<>(new String[]{"UCS", "GBFS", "A*"});
        startNodeField = new JTextField();
        goalNodeField = new JTextField();

        fieldsPanel.add(new JLabel("Start Word: "));
        fieldsPanel.add(startNodeField);
        fieldsPanel.add(new JLabel("Goal's Word: "));
        fieldsPanel.add(goalNodeField);
        fieldsPanel.add(new JLabel("Choose Algorithm: "));
        fieldsPanel.add(algorithmComboBox);

        searchButton = new JButton("Start Searching!");
        searchButton.setPreferredSize(new Dimension(50, 30));
        searchButton.addActionListener(e -> wlSearch());

        inputPanel.add(fieldsPanel, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.SOUTH);

        frame.add(inputPanel, BorderLayout.NORTH);
    }

    public void setupInfoArea() {
        infoArea = new JTextArea(3, 20);
        infoArea.setEditable(false);
        frame.add(new JScrollPane(infoArea), BorderLayout.SOUTH);
    }

    public void setupResultPanel() {
        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout()); 
        frame.add(resultPanel, BorderLayout.CENTER);
    }

    public void wlSearch() {
        String startNode = startNodeField.getText().toUpperCase().trim();
        String goalNode = goalNodeField.getText().toUpperCase().trim();
        String filename = "src/dictionary.txt";
        DictLoader dictLoader = new DictLoader(filename);
        Set<String> dict = dictLoader.getDict();

        if (startNode.isEmpty() || goalNode.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both start and goal word.");
            return;
        }

        if (startNode.length() != goalNode.length()) {
            JOptionPane.showMessageDialog(frame, "Error: Start and goal word must be of the same length.");
            return;
        }

        if (!WordLadderUtils.isInDict(startNode, dict) && !WordLadderUtils.isInDict(goalNode, dict)) {
            JOptionPane.showMessageDialog(frame, "Please enter valid words.");
            return; 
        }

        WordLadderUtils.WordLadderResult result = null;
        switch ((String) algorithmComboBox.getSelectedItem()) {
            case "UCS" -> result = UCS.ucsWordLadder(startNode, goalNode, dict);
            case "GBFS" -> result = GBFS.gbfsWordLadder(startNode, goalNode, dict);
            case "A*" -> result = AStar.aStarWordLadder(startNode, goalNode, dict);
            default -> JOptionPane.showMessageDialog(frame, "Algorithm not implemented yet");
        }

        resultPanel.removeAll(); 

        if (result != null && result.getWordLadderPath() != null) {
            WLTable wlTable = new WLTable(result.getWordLadderPath());
            JTable table = wlTable.createTable();
            resultPanel.add(new JScrollPane(table), BorderLayout.CENTER); 
            infoArea.setText("Path Length: " + result.getWordLadderPath().size() + "\nVisited Node's Amount: " 
            + result.getVisitedNum() + "\nExecution Time: " + result.getExecTime() + "ms\n");

        } else {
            JTextArea msgArea = new JTextArea("No path found between " + startNode + " and " + goalNode);
            resultPanel.add(new JScrollPane(msgArea), BorderLayout.CENTER); 
            infoArea.setText("Visited Node's Amount: " + result.getVisitedNum() + "\nExecution Time: " 
            + result.getExecTime() + "ms\n");
        }

        frame.revalidate();
        frame.repaint();
    }

   


    public static class WLTable {
        private String[][] wlTableData;

        public WLTable(java.util.List<String> wlPath) {
            wlTableData = new String[wlPath.size()][1];
            for (int i = 0; i < wlPath.size(); i++) {
                wlTableData[i][0] = wlPath.get(i);
            }
        }

        public JTable createTable() {
            int size = wlTableData.length;
            String goal = wlTableData[size-1][0];
            String[] columnNames = {size + "-Step Solution"};
            JTable table = new JTable(wlTableData, columnNames);
            
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent
                    (table, value, isSelected, hasFocus, row, column);
                    String word = (String) value;
                    label.setText(highlightDiff(word, goal));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    return label;
                }
            };
            table.getColumnModel().getColumn(0).setCellRenderer(renderer);

            table.setRowHeight(30);
            table.setFont(new Font("Arial", Font.BOLD, 20));
            return table;
        }

        public static String highlightDiff(String word1, String goal){
            StringBuilder result = new StringBuilder("<html>");
            for (int i=0; i<word1.length(); i++){
                if (word1.charAt(i) == goal.charAt(i)){
                    result.append("<font color='green'>").append(word1.charAt(i)).append("</font>");
                }
                else{
                    result.append(word1.charAt(i));
                }
            }
            result.append("</html>");
            return result.toString();
        }
    
    
    
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(WordLadderGUI::new); // Corrected class name
}
}
