package cn.huat.util;

public class EasyResult {

    // 响应业务状态
    private Integer resultCode;

    // 响应消息
    private String resultMessage;

    // 响应中的数据
    private Object resultData;

    public static EasyResult build(Integer code, String msg, Object data) {
        return new EasyResult(code, msg, data);
    }

    public static EasyResult ok(Object data) {
        return new EasyResult(data);
    }

    public static EasyResult ok() {
        return new EasyResult(null);
    }

    public EasyResult() {

    }

    public static EasyResult build(Integer errno, String msg) {
        return new EasyResult(errno, msg, null);
    }

    public EasyResult(Integer code, String msg, Object data) {
        this.resultCode = code;
        this.resultMessage = msg;
        this.resultData = data;
    }

    public EasyResult(Object data) {
        this.resultCode = 0;
        this.resultMessage = "OK";
        this.resultData = data;
    }

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}

    

}
