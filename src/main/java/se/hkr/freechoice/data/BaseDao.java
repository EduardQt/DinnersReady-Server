package se.hkr.freechoice.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao<TModel extends Model> {

    private final Class<TModel> clazz;
    private final ConnectionProvider connectionProvider;

    protected BaseDao(Class<TModel> clazz, ConnectionProvider connectionProvider) {
        this.clazz = clazz;
        this.connectionProvider = connectionProvider;
    }

    protected TModel selectSingle(String query, Object... parameters) {
        try {
            Connection connection = this.connectionProvider.provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (parameters != null)
                for (int i = 1; i <= parameters.length; i++) {
                    preparedStatement.setObject(i, parameters[i - 1]);
                }

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TModel tModel = this.clazz.newInstance();
                tModel.serialize(resultSet);

                return tModel;
            }
        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    protected boolean insert(String query, Object... parameters) {
        try {
            Connection connection = this.connectionProvider.provideConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if (parameters != null)
                for (int i = 1; i <= parameters.length; i++) {
                    preparedStatement.setObject(i, parameters[i - 1]);
                }

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
