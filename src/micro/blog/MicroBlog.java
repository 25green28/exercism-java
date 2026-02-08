package micro.blog;// TODO: THIS EXERCISE IS NOT FINISHED!

class MicroBlog {
    public String truncate(String input) {
        return input.substring(0, input.offsetByCodePoints(0, Math.min(input.codePointCount(0, input.length()), 5)));
    }
}

class RunMicroBlog {
    public static void main(String[] args) {
        MicroBlog mb = new MicroBlog();
        System.out.println(mb.truncate("💖✔️👨‍💻😀🗑️😕"));
    }
}