/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class ConnectionUtils {
      /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
   public static Connection getMyConnection() throws SQLException,
          ClassNotFoundException {
      // Sử dụng Oracle.
      return ConnectionOracle.getConnection();
  }
 
  //
  // Test Connection ...
  //
  public static void main(String[] args) throws SQLException,
          ClassNotFoundException {
 
      System.out.println("Get connection ... ");
 
      // Lấy ra đối tượng Connection kết nối vào database.
      /*Connection conn = ConnectionUtils.getMyConnection();
 
      System.out.println("Get connection " + conn);
 
      System.out.println("Thành công!");
      
      // viet sql
      String sql = "select * from khachhang";
      try(Statement statement = conn.createStatement();
              ResultSet result = statement.executeQuery(sql);){
          while(result.next()){
              int id = result.getInt("ID_KHACHHANG");
              String hoTen = result.getString("HOTEN");
              String sdt = result.getString("SDT");
              String email = result.getString("EMAIL");
              String cccd = result.getString("CCCD");
              Date nsinh = result.getDate("NGAYSINH");
              String gtinh = result.getString("GIOITINH");
              
              System.out.printf("ID %d | ten %s | sdt %s | mail %s | cccd %s | ngaysinh %s | gioi tinh %s\n",
                      id, hoTen, sdt,email,cccd,nsinh,gtinh);
          }
      }*/
  }
}
