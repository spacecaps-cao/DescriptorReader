# DescriptorReader

A simple tool that can convert OpenCV image descriptor to String and vice versa, written in Java.

When using [ORB](https://en.wikipedia.org/wiki/ORB_(feature_descriptor)) algorithm with OpenCV, you get a `descriptor` that represent the feature of an image. And then, you will match two descriptors with matching algorithm like Brute-force Hamming or FLANN. Finally you will get the similarity between two images.

But if you are facing a problem that contains a large number of reference images, the time consumption of accessing from disk is hard to accept. One conceivable solution is store the images into database. To improve the efficiency, we convert the descriptor(OpenCV Mat) into String instead of storing the serialized binary stream.

# Usage

> Descriptor => String

```
String str = descriptor2String(oneDescriptor);
```

> String => Descriptor 

```
Mat descriptor = string2Descriptor(str);
```

# How it works

A descriptor is a OpenCV Mat:

```
row 0: |0|1|2|3|...|29|30|31|
row 1: |0|1|2|3|...|29|30|31|
......
row N: |0|1|2|3|...|29|30|31|
```

It is a 2-D array that have 32 columns. Elements on this array are `double`, but the decimal part is meaningleess zero, so we can just use `int` and ignore the dot and zero. 

Then, use `StringBuffer` to convert the integer array into String with the separator `,`:

```
37, 19, 30, 178, ....
```

When we need to use the stored data from database, convert it in turn. The length of this sequence is unknow, but it must be divisible by 32. So we first create a empty array with specific dimension and then assign values iteratly.
