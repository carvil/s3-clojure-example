# s3-clojure-example

An example of how to read files from S3 in Clojure.

## Usage

First, install the dependencies:

    lein deps

Then, create a YAML file containing your S3 credentials:

    cp config/s3.example.yml config/s3.config

And change the contents of the file accordingly. It is assumed you have a file
in the specified S3 bucket.

Then, in the `repl`, you should be able to read the contents of a file:

    (slurp (download-object "<YOUR BUCKET NAME>" "<YOUR FILE NAME>"))

Or list the contents of the bucket:

    (list-bucket)

Finally, you can run the tests with:

    lein test

## License

Copyright © 2012 Carlos Vilhena

Distributed under the Eclipse Public License, the same as Clojure.
