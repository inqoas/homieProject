package tw.idv.tibame.tha102.core.util;

import static tw.idv.tibame.tha102.core.util.Constants.GSON;
import static tw.idv.tibame.tha102.core.util.Constants.JSON_MIME_TYPE;

import java.io.BufferedReader;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommonUtil {
	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
