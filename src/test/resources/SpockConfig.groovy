import simplemapper.testsupport.Slow

runner {
    if (!Boolean.getBoolean("test.include.slow")){
        exclude Slow
    }
}