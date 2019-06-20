package jp.co.tokyo_gas.batch.itemwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component("myWriter")
public class MyWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("fetch size:"+items.size());
		for (String str : items) {
			System.out.println(str);
		}
	}

}
