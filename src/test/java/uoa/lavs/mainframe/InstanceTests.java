package uoa.lavs.mainframe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstanceTests {
    @Test
    public void getInstanceReturnsConnection()
    {
        // Act
        Connection conn = Instance.getConnection();

        // Assert
        assertNotNull(conn);
    }

}