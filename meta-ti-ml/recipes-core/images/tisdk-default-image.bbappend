PR:append = "_tisdk_ml_0"

IMAGE_INSTALL:append = " \
    tensorflow-lite \
    onnx \
    onnxruntime \
    onnxruntime-tests \
    armnn \
    arm-compute-library \
    arm-compute-library-tests \
    nnstreamer \
    nnshark \
    analytics-demo-data \
"
