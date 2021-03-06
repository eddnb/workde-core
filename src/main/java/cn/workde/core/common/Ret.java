package cn.workde.core.common;

import cn.workde.core.db.Paginator;

import java.util.HashMap;

public class Ret extends HashMap {

    private static final String STATE = "state";

    private static final Integer STATE_OK = 1;

    private static final Integer STATE_FAIL = 0;

    private static final Integer STATE_UN_AUTHORIZED = -1;

    public Ret() {
    }

    public static Ret by(Object key, Object value) {
        return new Ret().set(key, value);
    }

    public static Ret create(Object key, Object value) {
        return new Ret().set(key, value);
    }


    public Ret setOk() {
        super.put(STATE, STATE_OK);
        return this;
    }

    public static Ret create() {
        return new Ret();
    }

    public static Ret ok() {
        return new Ret().setOk();
    }

    public static Ret ok(String msg) {
        return new Ret().setOk().msg(msg);
    }

    public static Ret ok(Object key, Object value) {
        return ok().set(key, value);
    }

    public static Ret fail() {
        return new Ret().setFail();
    }

    public static Ret fail(String msg) {
        return new Ret().setFail().msg(msg);
    }

    public static Ret state(Integer state) {
        return new Ret().set(STATE, state);
    }

    public static Ret fail(Object key, Object value) {
        return fail().set(key, value);
    }

    public static Ret auth() { return state(STATE_UN_AUTHORIZED); }

    public Ret setFail() {
        super.put(STATE, STATE_FAIL);
        return this;
    }

    public boolean isOk() {
        Object state = get(STATE);
        if (STATE_OK.equals(state)) {
            return true;
        }
        if (STATE_FAIL.equals(state)) {
            return false;
        }

        throw new IllegalStateException("调用 isOk() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
    }

    public boolean isFail() {
        Object state = get(STATE);
        if (STATE_FAIL.equals(state)) {
            return true;
        }
        if (STATE_OK.equals(state)) {
            return false;
        }

        throw new IllegalStateException("调用 isFail() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
    }

    public Ret set(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public Ret msg(String msg) {
        super.put("msg", msg);
        return this;
    }

    public Ret data(Object data) {
        super.put("data", data);
        return this;
    }

    public Ret paged(Paginator paged) {
        set("total", paged.getTotal()).set("pageIndex", paged.getPageIndex()).set("data", paged.getData()).set("totalPage", paged.getTotalPage());
        return this;
    }
}
