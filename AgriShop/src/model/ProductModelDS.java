package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;




public class ProductModelDS implements ProductModel<prodotto>{

	private DataSource ds= null;
	
	public ProductModelDS(DataSource ds) {
		this.ds=ds;
	}
	
	
	@Override
	public Collection<prodotto> doRetrieveAll(String order) throws SQLException {
		Connection connection=null;
		PreparedStatement prepareStatement=null;
		
		String selectSQL="SELECT * FROM prodotto";
		
		if(order!=null&&!order.equals("")) {
			selectSQL +="ORDER BY" +order;
		}
		
		Collection <prodotto> prodotti= new LinkedList<prodotto>(); 
		
		
		try {
			
			connection= ds.getConnection();
			prepareStatement= connection.prepareStatement(selectSQL);
			
			ResultSet rs=prepareStatement.executeQuery();
			
			while(rs.next()) {
				prodotto bean= new prodotto();
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setNome(rs.getString("nomep"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setQuantita(rs.getInt("disponibilitÓ"));
				bean.setSsn(rs.getString("ssn"));
			
				prodotti.add(bean);
				
				System.out.print(bean.nome);
				
			}
			
			
			
		}finally {
			try {
			if(prepareStatement!=null)
			prepareStatement.close();
			}finally {
				
			
			
			if(connection!=null)
			connection.close();
			}
			
		}
		
		return prodotti;
	}

	

	@Override
	public prodotto doRetrieveByKey(String code) throws SQLException {
		return null;
	}
	
	
	@Override
	public void doSave(prodotto item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doUpdate(prodotto item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDelete(prodotto item) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	
}
