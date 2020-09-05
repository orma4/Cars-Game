package graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import vehicles.Car;
import vehicles.Carriage;
import vehicles.Observer;
import vehicles.Vehicle;

public class Info implements Observer {
	private static Info info;
	private JFrame frame;
	String[][] data;
	String[] columnNames = { "Vehicle", "ID", "Color", "Wheels", "Speed",
			"Fuel amount", "Distance", "Fuel Consumption", "Lights",
			"Crashed With" };

	public static Info getInstance() {
		if (info == null)
			info = new Info();
		return info;
	}

	private Info() {

		frame = new JFrame();
		frame.setTitle("Vehicles Table");
		// close frame event
		// frame.addWindowListener(new java.awt.event.WindowAdapter()
		// {
		// @Override
		// public void windowClosing(java.awt.event.WindowEvent windowEvent)
		// {
		// frame.setVisible(false);
		// }
		// });
	}

	public void showOrHide() {
		if (CityPanel.getVehicleNum() == 0) {
			JOptionPane.showMessageDialog(frame, "There isn't any vehicle yet",
					"Error", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		// frame.setVisible(!frame.isVisible());
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void update() {
		if (frame.isVisible()) {
			data = new String[CityPanel.getInstance().getVehicleList().size()][11];
			JTable vTable = new JTable(data, columnNames);
			// DefaultTableModel model = (DefaultTableModel)vTable.getModel();
			// TableModel tableModel = new DefaultTableModel(data, columnNames);
			// JTable vTable = new JTable(tableModel);
			// vTable.setModel(tableModel);
			// DefaultTableModel model = new DefaultTableModel();
			// model.setColumnIdentifiers(columnNames);
			// vTable.setModel(model);
			vTable.setBounds(100, 100, 500, 300);
			int i = 0;
			for (Vehicle rowVehicle : CityPanel.getInstance().getVehicleList()) {
				int j = 0;
				data[i][j++] = new String(rowVehicle.getVehicleName());
				data[i][j++] = new String(Integer.toString(rowVehicle
						.getNumber()));
				data[i][j++] = new String(rowVehicle.getColor().name());
				data[i][j++] = new String(Integer.toString(rowVehicle
						.getWheels()));
				data[i][j++] = new String(Integer.toString(rowVehicle
						.getSpeed()));
				if (rowVehicle instanceof Car) {
					data[i][j++] = new String(
							Double.toString(((Car) rowVehicle).getFuelAmount()));
				} else if (rowVehicle instanceof Carriage) {
					data[i][j++] = new String(
							Integer.toString(((Carriage) rowVehicle).getP()
									.getEnergy()));
				} else {
					// Bike
					data[i][j++] = new String("0");
				}
				data[i][j++] = new String(Double.toString(rowVehicle.getKm()));
				data[i][j++] = new String(Integer.toString(rowVehicle
						.getFuelConsumption()));
				data[i][j++] = new String(Boolean.toString(rowVehicle
						.isLights()));
				data[i][j++] = new String(rowVehicle.getCrashedWith());
				i++;
				// ((DefaultTableModel) tableModel).addRow(data);

				System.out.println("here we");
				JScrollPane sp = new JScrollPane(vTable,
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				JScrollBar bar = sp.getVerticalScrollBar();
				bar.setPreferredSize(new Dimension(4000, 1100));
				frame.add(sp);
				frame.setSize(950, 300);
				TableColumnModel tcm = vTable.getColumnModel();
				tcm.getColumn(0).setPreferredWidth(200); // Vehicle
				tcm.getColumn(1).setPreferredWidth(45); // ID
				tcm.getColumn(2).setPreferredWidth(100); // Color
				tcm.getColumn(3).setPreferredWidth(100); // Wheels
				tcm.getColumn(4).setPreferredWidth(100); // Speed
				tcm.getColumn(5).setPreferredWidth(180); // Fuel Amount
				tcm.getColumn(6).setPreferredWidth(150); // Distance
				tcm.getColumn(7).setPreferredWidth(225); // Fuel Consumption
				tcm.getColumn(8).setPreferredWidth(100); // Lights
				tcm.getColumn(9).setPreferredWidth(200); // Crashed with

				// DefaultTableModel model =
				// (DefaultTableModel)vTable.getModel();
				// tableModel.setRowCount(0);
				// vTable.setModel(model);
				frame.setVisible(true);
			}
		}
	}

}
