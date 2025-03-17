PR:append = "_tisdk_ml_0"

IMAGE_INSTALL:append = " \
    tensorflow-lite \
    onnx \
    onnxruntime \
    armnn \
    arm-compute-library \
    nnstreamer \
    nnshark \
    analytics-demo-data \
"
