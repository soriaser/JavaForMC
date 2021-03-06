package jmc.converter;

import org.apache.bcel.classfile.Method;

public class JCReducedMethod {

    public final static byte SIZE_HEADER = 8;

    private final static byte FLAG_INIT_METHOD = 0x01;

    private final static byte FLAG_CLINIT_METHOD = 0x02;

    private final static byte FLAG_ONEVENT_METHOD = 0x04;

    private JCReducedCode code;

    private byte arguments;

    private byte locals;

    private byte stack;

    private byte id;
    
    private byte idc;

    private boolean isInit;

    private boolean isClinit;

    private boolean isMain;

    private boolean isOnEvent;

    public JCReducedMethod(Method method, byte id, byte idc) {
        this.id  = id;
        this.idc = idc;

        this.code = new JCReducedCode(method.getCode().getCode());

        if (method.getCode().getMaxLocals() > 255) {
            throw new InternalError("Maximum locals exceed maximum allowed");
        }
        
        this.locals = (byte) method.getCode().getMaxLocals();

        if (method.getCode().getMaxStack() > 255) {
            throw new InternalError("Maximum stack exceed maximum allowed");
        }
        
        this.stack = (byte) method.getCode().getMaxStack();

        this.isInit = false;
        if ((method.getName().equals(JCReducedConstants.API_METHOD_INIT)) &&
                (method.getSignature().equals("()V"))) {
            this.isInit = true;
        }

        this.isClinit = false;
        if ((method.getName().equals(JCReducedConstants.API_METHOD_CLINIT)) &&
                (method.getSignature().equals("()V"))) {
            this.isClinit = true;
        }

        this.isMain = false;
        if ((method.getName().equals(JCReducedConstants.API_METHOD_MAIN)) &&
                (method.getSignature().equals("()V"))) {
            this.isMain = true;
        }

        this.isOnEvent = false;
        if ((method.getName().equals(JCReducedConstants.API_METHOD_ONEVENT)) &&
                (method.getSignature().equals("(B)V"))) {
            this.isOnEvent = true;
        }

        this.arguments = JCParser.getNumberOfArguments(method);
    }

    public byte[] getByteCode() {
        return this.code.getCode();
    }

    public byte getClassId() {
        return this.idc;
    }

    public byte getFlags() {
        byte flags = 0x00;

        if (this.isInit) {
            flags |= FLAG_INIT_METHOD;
        }

        if (this.isClinit) {
            flags |= FLAG_CLINIT_METHOD;
        }

        if (this.isOnEvent) {
            flags |= FLAG_ONEVENT_METHOD;
        }

        return flags;
    }

    public byte getId() {
        return this.id;
    }

    public byte getMaximumLocals() {
        return this.locals;
    }

    public byte getMaximumStack() {
        return this.stack;
    }

    public byte getNumberOfArguments() {
        return this.arguments;
    }

    public boolean isClinit() {
        return this.isClinit;
    }    

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isMain() {
        return this.isMain;
    }

    public boolean isOnEvent() {
        return this.isOnEvent;
    }

    public void setId(byte id) {
        this.id = id;
    }

}
