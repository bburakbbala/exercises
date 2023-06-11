using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IDepartmentRepository : IRepositoryAsync<Department>
    {
        void Update(Department departmant);
    }
}
