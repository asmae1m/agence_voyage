package dao;

import java.util.ArrayList;

import beans.Theme;

public interface IThemesDao {
	void setTheme(Theme t);
	public ArrayList<Theme> getThemesById(int voyageId);
}
