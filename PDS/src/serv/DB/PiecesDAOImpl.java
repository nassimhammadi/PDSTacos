package serv.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import serv.model.Breakdown;
import serv.model.BreakdownList;
import serv.model.ListPieces;
import serv.model.Pieces;

public class PiecesDAOImpl implements PiecesDAO{
	
	private Connection connection;
	private Statement ordre;


	/**
	 * Class contructor
	 * @param daoFactory an instance of DAOFactory
	 */
	public PiecesDAOImpl(Connection c) {
		this.connection = c;
	}

	@Override
	public void insert(Pieces b) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pieces find(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized ListPieces findList(int id) throws DAOException {
		Pieces b;
		ListPieces list = null;
		ArrayList<Pieces> a_p = new ArrayList<Pieces>();
		try {
			ordre = connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		String sql = "select * from PIECES p join BREAKDOWNSPIECES bp on p.ID_PIECE=bp.ID_PIECE where bp.ID_BREAKDOWN="+id;

		try {
			ResultSet rs = ordre.executeQuery(sql);
			while(rs.next()){
				int id_piece = rs.getInt(1) ;
				String name = rs.getString(2) ;
				int cost = rs.getInt(3) ;
				int stock = rs.getInt(4) ;
				
				b = new Pieces(id_piece, name, cost, stock);
				a_p.add(b);
			}
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}


		try {
			ordre.close();
		} catch (SQLException ex) {
			Logger.getLogger(BreakdownDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
		}
		list = new ListPieces(a_p);
		return list;
	}

	@Override
	public void update(Pieces b) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
