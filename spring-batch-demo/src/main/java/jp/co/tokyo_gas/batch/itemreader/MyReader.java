package jp.co.tokyo_gas.batch.itemreader;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyReader implements ItemReader<String> {

	private Iterator<String> iterator;

	public MyReader(List<String> list) {
		this.iterator = list.iterator();
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (iterator.hasNext()) {
			return this.iterator.next();
		} else {
			return null;
		}
	}

}
