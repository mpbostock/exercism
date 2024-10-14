class Badge {
    public String print(Integer id, String name, String department) {
        StringBuilder builder = new StringBuilder();
        if (id != null) {
            builder.append('[').append(id).append(']').append(" - ");
        }
        builder.append(name).append(" - ");
        builder.append(department != null ? department.toUpperCase() : "OWNER");
        return builder.toString();
    }
}
