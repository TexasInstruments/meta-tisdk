PR:append = "_tisdk_ml_0"

# microsoft/onnxruntime GitHub repo is unavailable. Use the TI fork instead which is on 1.23 release
SRC_URI:remove = "git://github.com/microsoft/onnxruntime.git;protocol=https;branch=rel-1.23.2"
SRC_URI:prepend = "git://github.com/TexasInstruments/onnxruntime.git;protocol=https;branch=tidl-1.23 "
SRCREV = "be835efc56aca19b8e810538ec93c8e150e0fc61"

