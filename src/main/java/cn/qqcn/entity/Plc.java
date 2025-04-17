package cn.qqcn.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName plc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plc{
    /**
     * 
     */
    private Integer plc_id;

    /**
     * 
     */
    private String plc_name;

    /**
     * 
     */
    private String plc_ip;

    /**
     * 
     */
    private Integer plc_port;

    /**
     * 连接状态
     */
    private String connection_status;

    /**
     * 产品系列
     */
    private String product_series;

    /**
     * 产品类型
     */
    private String product_type;

    /**
     * 额定电源电压
     */
    private String standard_voltage;

    /**
     * 离散输入数量
     */
    private String discrete_input_num;

    /**
     * 模拟输入数量
     */
    private String analogue_input_num;

    /**
     * 离散输出类型
     */
    private String discrete_output_type;

    /**
     * 离散输出数量
     */
    private String discrete_output_num;

    /**
     * 离散输出电压
     */
    private String discrete_output_voltage;

    /**
     * 离散输出电流
     */
    private String discrete_output_current;

    /**
     * 
     */
    private String plc_image;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Plc other = (Plc) that;
        return (this.getPlc_id() == null ? other.getPlc_id() == null : this.getPlc_id().equals(other.getPlc_id()))
            && (this.getPlc_name() == null ? other.getPlc_name() == null : this.getPlc_name().equals(other.getPlc_name()))
            && (this.getPlc_ip() == null ? other.getPlc_ip() == null : this.getPlc_ip().equals(other.getPlc_ip()))
            && (this.getPlc_port() == null ? other.getPlc_port() == null : this.getPlc_port().equals(other.getPlc_port()))
            && (this.getConnection_status() == null ? other.getConnection_status() == null : this.getConnection_status().equals(other.getConnection_status()))
            && (this.getProduct_series() == null ? other.getProduct_series() == null : this.getProduct_series().equals(other.getProduct_series()))
            && (this.getProduct_type() == null ? other.getProduct_type() == null : this.getProduct_type().equals(other.getProduct_type()))
            && (this.getStandard_voltage() == null ? other.getStandard_voltage() == null : this.getStandard_voltage().equals(other.getStandard_voltage()))
            && (this.getDiscrete_input_num() == null ? other.getDiscrete_input_num() == null : this.getDiscrete_input_num().equals(other.getDiscrete_input_num()))
            && (this.getAnalogue_input_num() == null ? other.getAnalogue_input_num() == null : this.getAnalogue_input_num().equals(other.getAnalogue_input_num()))
            && (this.getDiscrete_output_type() == null ? other.getDiscrete_output_type() == null : this.getDiscrete_output_type().equals(other.getDiscrete_output_type()))
            && (this.getDiscrete_output_num() == null ? other.getDiscrete_output_num() == null : this.getDiscrete_output_num().equals(other.getDiscrete_output_num()))
            && (this.getDiscrete_output_voltage() == null ? other.getDiscrete_output_voltage() == null : this.getDiscrete_output_voltage().equals(other.getDiscrete_output_voltage()))
            && (this.getDiscrete_output_current() == null ? other.getDiscrete_output_current() == null : this.getDiscrete_output_current().equals(other.getDiscrete_output_current()))
            && (this.getPlc_image() == null ? other.getPlc_image() == null : this.getPlc_image().equals(other.getPlc_image()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlc_id() == null) ? 0 : getPlc_id().hashCode());
        result = prime * result + ((getPlc_name() == null) ? 0 : getPlc_name().hashCode());
        result = prime * result + ((getPlc_ip() == null) ? 0 : getPlc_ip().hashCode());
        result = prime * result + ((getPlc_port() == null) ? 0 : getPlc_port().hashCode());
        result = prime * result + ((getConnection_status() == null) ? 0 : getConnection_status().hashCode());
        result = prime * result + ((getProduct_series() == null) ? 0 : getProduct_series().hashCode());
        result = prime * result + ((getProduct_type() == null) ? 0 : getProduct_type().hashCode());
        result = prime * result + ((getStandard_voltage() == null) ? 0 : getStandard_voltage().hashCode());
        result = prime * result + ((getDiscrete_input_num() == null) ? 0 : getDiscrete_input_num().hashCode());
        result = prime * result + ((getAnalogue_input_num() == null) ? 0 : getAnalogue_input_num().hashCode());
        result = prime * result + ((getDiscrete_output_type() == null) ? 0 : getDiscrete_output_type().hashCode());
        result = prime * result + ((getDiscrete_output_num() == null) ? 0 : getDiscrete_output_num().hashCode());
        result = prime * result + ((getDiscrete_output_voltage() == null) ? 0 : getDiscrete_output_voltage().hashCode());
        result = prime * result + ((getDiscrete_output_current() == null) ? 0 : getDiscrete_output_current().hashCode());
        result = prime * result + ((getPlc_image() == null) ? 0 : getPlc_image().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", plc_id=").append(plc_id);
        sb.append(", plc_name=").append(plc_name);
        sb.append(", plc_ip=").append(plc_ip);
        sb.append(", plc_port=").append(plc_port);
        sb.append(", connection_status=").append(connection_status);
        sb.append(", product_series=").append(product_series);
        sb.append(", product_type=").append(product_type);
        sb.append(", standard_voltage=").append(standard_voltage);
        sb.append(", discrete_input_num=").append(discrete_input_num);
        sb.append(", analogue_input_num=").append(analogue_input_num);
        sb.append(", discrete_output_type=").append(discrete_output_type);
        sb.append(", discrete_output_num=").append(discrete_output_num);
        sb.append(", discrete_output_voltage=").append(discrete_output_voltage);
        sb.append(", discrete_output_current=").append(discrete_output_current);
        sb.append(", plc_image=").append(plc_image);
        sb.append("]");
        return sb.toString();
    }
}